package com.tlc.attachment.storage;

/**
 * <p>
 *    {@code ObjectUploadResponse} contains the object upload response information.
 * </p>
 *
 * @param versionId     refers the version id of the object
 * @param objectName    represents the name of the object
 * @param storageSource represents the storage service which stores the data
 *
 * @author ThalaimalaiPandiyanT
 * @version 1.0
 */
public record ObjectUploadResponse(String versionId,String objectName,String storageSource){

}


