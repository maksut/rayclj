package rayclj;

import java.io.File;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * jextract's RuntimeHelper classes does not support dynamic library resolution.
 * So provide a static method to resolve absolute path for raylib library.
 *
 * Also it is not possible to load native library from jar file. So if the user 
 * does not provide a path then copy platform specific library to current working directory.
 */
public final class LibraryResolver {
    public static String ResolveRaylib() {
        String library = System.getProperty("rayclj.library");

        if (library == null || library.isEmpty()) {
            String OS = System.getProperty("os.name");
            final String resource;

            if (OS.contains("Mac OS X")) {
                library = "libraylib.dylib";
                resource = "raylib-5.0_macos/lib/" + library;
            } else if (OS.contains("Windows")) {
                library = "raylib.dll";
                resource = "raylib-5.0_win64_msvc16/lib/" + library;
            } else {
                library = "libraylib.so"; // some Unix
                resource = "raylib-5.0_linux_amd64/lib/" + library;
            }

            if (!new File(library).exists()) { 
                // copy the dynamic library from jar to current working directory
                System.out.println("INFO: RAYCLJ: Library not found: " + library);
                InputStream is = LibraryResolver.class.getClassLoader().getResourceAsStream(resource);

                try {
                    Files.copy(is, Paths.get(library));
                    System.out.println("INFO: RAYCLJ: Library copied to current working directory: " + library);
                }
                catch (Exception e) {
                    throw new RuntimeException(e);
                }
            }
        }

        return new File(library).getAbsolutePath();
    }
}
