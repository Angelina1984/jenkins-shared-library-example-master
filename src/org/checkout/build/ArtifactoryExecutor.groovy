package org.checkout.build

import org.checkout.IStepExecutor
import org.checkout.ioc.ContextRegistry

class ArtifactoryExecutor implements Serializable {
    def artifactoryPath
    def artifactoryUrl
    def credentialsId

    ArtifactoryExecutor(artifactoryPath, artifactoryUrl, credentialsId) {
        this.artifactoryPath = artifactoryPath
        this.artifactoryUrl = artifactoryUrl
        this.credentialsId = credentialsId
    }

    /**
     * Downloads the given artifactory path to the given destination path.
     * @param destinationPath - our build directory
     */
    void downloadFromArtifactory(destinationPath){
        IStepExecutor steps = ContextRegistry.getContext().getStepExecutor()
        if (destinationPath) {
            steps.sh "mkdir -p ${destinationPath}"
            steps.sh "chmod 777 ${destinationPath}" // Otherwise Artifactory download won't have permission to write the files
        }
      /*
        // enable below code when you figure out Artifactory mocking
        try {
            def artifactoryServer = steps.Artifactory.newServer url: artifactoryUrl, credentialsId: credentialsId
            def downloadSpec = """{
                "files": [
                    {
                        "pattern": "${artifactoryPath}",
                        "target": "${destinationPath}",
                        "flat": "true"
                    }
                ]
            }"""
            artifactoryServer.download(downloadSpec)
            println "Successfully downloaded ${downloadSpec} from artifactory"

        } catch (Exception e) {
            steps.error "Caught exception while downloading from Artifactory: ${e}"
            throw e
        } */
    }
}
