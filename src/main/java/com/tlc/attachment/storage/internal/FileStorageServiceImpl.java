package com.tlc.attachment.storage.internal;

import com.tlc.attachment.service.FileStorageService;
import com.tlc.attachment.storage.status.StorageErrorCodes;
import com.tlc.commons.code.ErrorCode;
import com.tlc.commons.code.ErrorCodes;
import com.tlc.commons.util.Env;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.UUID;

/**
 * <p>
 *     Carries out the provided file storage-related services.
 * </p>
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 * @see com.tlc.attachment.service.FileStorageService
 */
public class FileStorageServiceImpl implements FileStorageService {

    private static final String FILE_DIRECTORY = Env.getWorkDirectory() + java.io.File.separator + "uploads";

    private FileStorageServiceImpl() {
    }

    private static class Instance {
        private static final FileStorageServiceImpl INSTANCE = new FileStorageServiceImpl();
    }

    /**
     * <p>
     *     Gets the instance of {@link FileStorageServiceImpl}
     * </p>
     *
     * @return the instance of {@link FileStorageServiceImpl}
     */
    public static FileStorageServiceImpl getInstance() {
        return Instance.INSTANCE;
    }

    /**
     * {@inheritDoc}
     *
     * @param inputStream represents the {@link java.io.InputStream} which contains the object data
     * @param extension   represents the file type
     * @param suffixPath  represents the uploaded file path
     * @return the uploaded path of the file
     */
    @Override
    public final Path uploadFile(final InputStream inputStream, final String extension, final String suffixPath) {
        final Path path = getFileWithAvailableName(suffixPath, extension);

        try (final OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(path.toFile()));
             inputStream) {
            final byte[] buffer = new byte[1024];
            int readOffset;

            while ((readOffset = inputStream.read(buffer, 0, buffer.length)) != -1) {
                outputStream.write(buffer, 0, readOffset);
            }
        } catch (IOException e) {
            throw ErrorCode.get(ErrorCodes.FILE_NOT_FOUND, e);
        }

        return path;
    }

    /**
     * <p>
     *     Retrieves the file with the specified name from the file path.
     * </p>
     *
     * @param suffixPath represents the path of the file
     * @param extension  represents the file type
     * @return the path of the created file
     */
    private Path  getFileWithAvailableName(final String suffixPath, final String extension) {
        final String filePrefixPath = String.format("%s%s%s", FILE_DIRECTORY, java.io.File.separator, suffixPath);

        try {
            Files.createDirectories(Path.of(filePrefixPath));

            java.io.File file = new java.io.File(String.format("%s%s%s.%s", filePrefixPath,
                    java.io.File.separator, UUID.randomUUID(), extension));
            final String absolutePath = file.getAbsolutePath();
            int count = 0;

            while (file.exists()) {
                file = new java.io.File(String.format("%s(%s).%s", absolutePath.substring(0, absolutePath.lastIndexOf(".")), ++count, extension));
            }

            return file.toPath();
        } catch (Exception exception) {
            throw ErrorCode.get(StorageErrorCodes.FILE_NOT_FOUND);
        }
    }

}
