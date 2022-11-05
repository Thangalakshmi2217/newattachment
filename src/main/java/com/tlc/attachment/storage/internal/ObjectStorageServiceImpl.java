package com.tlc.attachment.storage.internal;

import com.tlc.attachment.service.ObjectStorageService;
import com.tlc.attachment.storage.ObjectStorageType;
import com.tlc.attachment.storage.internal.config.ObjectStorageConfig;
import com.tlc.commons.code.ErrorCode;
import com.tlc.attachment.storage.ObjectDownloadResponse;
import com.tlc.attachment.storage.ObjectUploadResponse;
import com.tlc.attachment.storage.status.StorageErrorCodes;

import io.minio.BucketExistsArgs;
import io.minio.GetObjectResponse;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import io.minio.GetObjectArgs;
import io.minio.PutObjectArgs;
import io.minio.SetBucketVersioningArgs;
import io.minio.ObjectWriteResponse;
import io.minio.messages.VersioningConfiguration;

import java.io.InputStream;

/**
 * <p>
 *     Builds the bucket and gathers data about object uploads and download responses.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public class ObjectStorageServiceImpl implements ObjectStorageService {

    private final MinioClient minioClient;
    private final String bucketName;

    ObjectStorageServiceImpl(final ObjectStorageConfig config) {
        this.minioClient = config.getClient();
        this.bucketName = config.getBucketName();
    }

    /**
     * <p>
     *     Checks whether the bucket exists or not.
     * </p>
     *
     * @return true if the bucket exists
     */
    private boolean isBucketExist() {
        try {
            return minioClient.bucketExists(BucketExistsArgs.builder().bucket(bucketName).build());
        } catch (Exception exception) {
            throw ErrorCode.get(StorageErrorCodes.BUCKET_NOT_FOUND, exception.getMessage());
        }
    }

    /**
     * <p>
     *     Creates the bucket.
     * </p>
     */
    private void createBucket() {
        try {
            this.minioClient.makeBucket(MakeBucketArgs.builder().bucket(bucketName).build());
            this.setBucketVersioning();
        } catch (Exception exception) {
            throw ErrorCode.get(StorageErrorCodes.BUCKET_CREATION_FAILED);
        }
    }

    /**
     * <p>
     *     Enables the versioning configuration of a bucket to protect the objects.
     * </p>
     */
    private void setBucketVersioning() {
        try {
            this.minioClient.setBucketVersioning(SetBucketVersioningArgs.builder().bucket(bucketName)
                    .config(new VersioningConfiguration(VersioningConfiguration.Status.ENABLED, null)).build());
        } catch (Exception exception) {
            throw ErrorCode.get(StorageErrorCodes.BUCKET_VERSIONING_FAILED);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param versionId  represents the version id of the object
     * @param objectName represents the name of the object
     * @return the object download response information
     */
    @Override
    public ObjectDownloadResponse downloadObject(final String versionId, final String objectName) {
        if (isBucketExist()) {

            try (final InputStream inputStream = minioClient.getObject(GetObjectArgs.builder().bucket(bucketName)
                    .versionId(versionId).object(objectName).build())) {
                return new ObjectDownloadResponse(inputStream);
            } catch (Exception exception) {
                throw ErrorCode.get(StorageErrorCodes.OBJECT_DOWNLOAD_FAILED, exception.getMessage());
            }
        } else {
            throw ErrorCode.get(StorageErrorCodes.BUCKET_NOT_FOUND);
        }
    }

    /**
     * {@inheritDoc}
     *
     * @param inputStream represents the {@link java.io.InputStream} that contains the object data
     * @param objectName  represents the name of the object
     * @return the object upload response information
     */
    @Override
    public ObjectUploadResponse uploadObject(final InputStream inputStream, final String objectName) {
        if (!isBucketExist()) {
            createBucket();
        }

        return getUploadResponse(inputStream, objectName);
    }

    /**
     * <p>
     *     Upload an input stream of a given size and obtains data on the object upload response.
     * </p>
     *
     * @param inputStream represents the {@link java.io.InputStream} that contains the object data
     * @param objectName  represents the name of the object
     * @return the object upload response information
     */
    private ObjectUploadResponse getUploadResponse(final InputStream inputStream, final String objectName) {
        try (inputStream) {
            final ObjectWriteResponse writeResponse = minioClient.putObject(PutObjectArgs.builder()
                    .bucket(bucketName).object(objectName).stream(inputStream, inputStream.available(), -1).build());

            return new ObjectUploadResponse(writeResponse.versionId(), writeResponse.object(), ObjectStorageType.MINIO.name());
        } catch (Exception exception) {
            throw ErrorCode.get(StorageErrorCodes.OBJECT_UPLOAD_FAILED, exception.getMessage());
        }
    }

}