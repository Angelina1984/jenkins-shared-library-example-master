package org.checkout

/**
 *
 */
class Artifactory {
    private url
    private credentialsId

    ArtifactoryServer newServer(Map arg){
        this.url = arg.get("url")
        this.credentialsId = arg.get("credentialsId")
        return new ArtifactoryServer()
    }

    class ArtifactoryServer {
        private downloadSpec

        void download(downloadSpec){
            this.downloadSpec = downloadSpec
        }
    }
}
