package com.tlc.attachment;

import com.tlc.commons.code.ErrorCode;
import com.tlc.commons.code.ErrorCodes;
import com.tlc.commons.util.ConfLoader;
import com.tlc.attachment.storage.internal.StorageServiceImpl;
import com.tlc.attachment.service.StorageService;

import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.atomic.AtomicReference;

/**
 * <p>
 *     Activates and deactivates the storage access; registers and unregisters the storage service.
 * </p>
 *
 * @author ThalaimalaiPandiyan T
 * @version 1.0
 */
public class StorageAccess implements BundleActivator {

    private static final AtomicReference<StorageService> REFERENCE = new AtomicReference<>();

    /**
     * <p>
     *     Called when this bundle is started so the framework can perform the bundle-specific activities necessary to
     *     start this bundle and loads the storage services.
     * </p>
     *
     * @param context the execution context of the bundle being started
     * @throws Exception if this method throws an exception, this bundle is marked as stopped and the Framework will
     * remove this bundle's listeners, unregister all services registered by this bundle, and release all services used
     * by this bundle
     */
    @Override
    public void start(final BundleContext context) throws Exception {
        final StorageServiceImpl storageService = new StorageServiceImpl();
        final Map<String, String> input = ConfLoader.load("object.storage.cfg");

        storageService.start(input);
        register(storageService);
    }

    /**
     * <p>
     *     Called when this bundle is stopped so the framework can perform the bundle-specific activities necessary to
     *     stop the bundle.
     * </p>
     *
     * @param context the execution context of the bundle being stopped
     * @throws Exception if this method throws an exception, the bundle is still marked as stopped, and the framework
     * will remove the bundle's listeners, unregister all services registered by the bundle, and release all services
     * used by the bundle.
     */
    @Override
    public void stop(final BundleContext context) throws Exception {
        final StorageServiceImpl storageService = (StorageServiceImpl) unregister();

        storageService.stop();
    }

    /**
     * <p>
     *     Registers the storage service.
     * </p>
     *
     * @param service represents the {@link StorageService} object
     */
    private static void register(final StorageService service) {
        if (!REFERENCE.compareAndSet(null, service)) {
            throw ErrorCode.get(ErrorCodes.INVALID_ACCESS, "Service already initialized");
        }
    }

    /**
     * <p>
     *     Unregisters the storage service.
     * </p>
     *
     * @return the storage service object
     */
    private static StorageService unregister() {
        return REFERENCE.getAndSet(null);
    }

    /**
     * <p>
     *     Gets the storage service.
     * </p>
     *
     * @return the storage service
     */
    public static StorageService get() {
        final StorageService service = REFERENCE.get();

        if (Objects.isNull(service)) {
            throw ErrorCode.get(ErrorCodes.INVALID_ACCESS, "Service not initialized");
        } else {
            return service;
        }
    }

}