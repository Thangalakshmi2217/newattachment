package com.tlc.attachment.storage.internal.config;

import io.minio.MinioClient;

import java.util.Map;
import java.util.Objects;

/**
 * <p>
 *     Provides object storage services.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public class ObjectStorageConfig {

    private final MinioClient minioClient;
    private final String bucketName;

    public ObjectStorageConfig(final Map<String, String> input) {
        final String endpoint = String.format("http://%s", Objects.requireNonNull(input.get("storage.server").trim()));
        this.minioClient = MinioClient.builder()
                .endpoint(endpoint)
                .credentials(Objects.requireNonNull(input.get("storage.accessKey").trim()), Objects.requireNonNull(input.get("storage.secretKey").trim()))
                .build();
        this.bucketName = Objects.requireNonNull(input.get("storage.bucket"));
    }

    /**
     * <p>
     *     Gets the client.
     * </p>
     *
     * @return the object storage server client
     */
    public MinioClient getClient() {
        return minioClient;
    }

    /**
     * <p>
     *     Gets the bucket name.
     * </p>
     *
     * @return the bucket name
     */
    public String getBucketName() {
        return bucketName;
    }

}
