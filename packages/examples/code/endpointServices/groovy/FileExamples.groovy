package endpointServices.groovy;

import java.nio.file.Path

class FileExamples {

    /**
     * Logs the name of the file to the console.
     * This is a Groovy service that can be used with an FTP client or Directory watcher endpoint.
     * @param file the file to log
     */
    void logFileChange( Path file ) {
        "Endpoint called for file ${file}".info()
    }

    /**
     * Sends a copy of the provided file to the configured FTP server.
     * Requires {@code ftp.*} package properties to be set.
     * This is a Groovy service that can be used with an FTP client or Directory watcher endpoint.
     * @param bytes the content of the file in bytes
     * @param filename the filename of the file
     */
    void sendToRemoteLocation( byte[] bytes, String filename ) {
        String username = 'ftp.username'.getPackageProperty()
        String password = 'ftp.password'.getPackageProperty()
        String host = 'ftp.host'.getPackageProperty()
        String protocol = 'ftp.protocol'.getPackageProperty()
        "Received file with a name ${filename}".info()
        bytes.sendTo("${protocol}://${URLEncoder.encode(username, 'UTF-8')}}:${URLEncoder.encode(password, 'UTF-8')}@${host}/${filename}")
    }

}