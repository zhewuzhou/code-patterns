```kotlin
//The caller
override fun doTaskAction() {
    getWorkerFacadeWithWorkers().use {
        it.submit(
            BundleLibraryJavaResRunnable::class.java,
            BundleLibraryJavaResRunnable.Params(
                output = output!!.get().asFile,
                inputs = unfilteredResources.files,
                jarCreatorType = jarCreatorType,
                compressionLevel = if (debuggable.get()) Deflater.BEST_SPEED else null
            )
        )
    }
}

//The Extension Method
public inline fun <T : Closeable?, R> T.use(block: (T) -> R): R {
    var exception: Throwable? = null
    try {
        return block(this)
    } catch (e: Throwable) {
        exception = e
        throw e
    } finally {
        when {
            apiVersionIsAtLeast(1, 1, 0) -> this.closeFinally(exception)
            this == null -> {}
            exception == null -> close()
            else ->
                try {
                    close()
                } catch (closeException: Throwable) {
                    // cause.addSuppressed(closeException) // ignored here
                }
        }
    }
}
```
