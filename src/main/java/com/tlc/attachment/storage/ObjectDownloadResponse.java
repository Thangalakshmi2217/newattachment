package com.tlc.attachment.storage;

import java.io.InputStream;

/**
 * <p>
 *     {@code ObjectDownloadResponse} contains the object download response information.
 * </p>
 *
 * @param stream interprets the information in the object download response
 *
 * @author ThalaimalaiPandiyan T
 * @version 1.0
 */
public record ObjectDownloadResponse(InputStream stream) {
}
