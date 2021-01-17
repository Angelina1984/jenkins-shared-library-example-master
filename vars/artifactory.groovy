import org.checkout.build.ArtifactoryExecutor
import org.checkout.ioc.ContextRegistry

/**
 * Downloads the given artifactory path to the given destination path.
 * Pass following parameters when calling downloadFrom:
 *  @param config.artifactoryPath - artifactory path
 *  @param config.destinationPath - our build directory
 *  @param config.artifactoryUrl - artifactory url
 *  @param config.credentialsId - artifactory credentials id stored in jenkins
 * */
def downloadFrom(Map config=[:]) {
    println 'in vars/artifactory.downloadFrom'
    ContextRegistry.registerDefaultContext(this)
    def artifactory = new ArtifactoryExecutor(config.artifactoryPath, config.artifactoryUrl, config.credentialsId)
    artifactory.downloadFromArtifactory(config.destinationPath)
    println 'completed vars/artifactory.downloadFrom'
    /*if (config.destinationPath) {
        sh "mkdir -p ${config.destinationPath}"
        sh "chmod 777 ${config.destinationPath}" // Otherwise Artifactory download won't have permission to write the files
    }
    try {
        def artifactoryServer = Artifactory.newServer url: config.artifactoryUrl, credentialsId: config.credentialsId
        def downloadSpec = """{
            "files": [
                {
                    "pattern": "${config.artifactoryPath}",
                    "target": "${config.destinationPath}",
                    "flat": "true"
                }
            ]
        }"""
        artifactoryServer.download(downloadSpec)
        log.info "Successfully downloaded ${downloadSpec} from artifactory"

    } catch (Exception e) {
        log.error "Caught exception while downloading from Artifactory: ${e}"
        throw e
    }*/
}

/**
 * Checks Artifactory for the last release to have been uploaded.
 * Pass following parameters when calling getLatestRelease:
 *  @param config.artifactoryPath - artifactory path
 *  @param config.artifactoryUrl - artifactory url
 *  @param config.credentialsId - artifactory credentials id stored in jenkins
 */
/*def getLatestRelease(Map config=[:]) {
    String releaseJsonPath = null
    withCredentials([[
         $class: 'UsernamePasswordMultiBinding',
         credentialsId: config.credentialsId,
         usernameVariable: 'USERNAME',
         passwordVariable: 'PASSWORD'
    ]]) {
        // Search for last modified release.json using Artifactory API
        String lastModifiedInfoJsonString = sh(script: "curl -u ${USERNAME}:${PASSWORD} ${config.artifactoryUrl}api/storage/${config.artifactoryPath}?lastModified", returnStdout: true)
        def lastModifiedInfoJson = readJSON text: lastModifiedInfoJsonString

        // Fetch last modified release.json using Artifactory API.
        String releaseInfoJsonString = sh(script: "curl -u ${USERNAME}:${PASSWORD} ${lastModifiedInfoJson.uri}", returnStdout: true)
        def releaseInfoJson = readJSON text: releaseInfoJsonString
        releaseJsonPath = releaseInfoJson.path
        log.info "Path to the last modified release: ${releaseJsonPath}"
    }

    String releaseJsonFileName = releaseJsonPath.substring(releaseJsonPath.lastIndexOf('/') + 1, releaseJsonPath.length())
    String artifactoryPath = "${config.artifactoryPath}/${releaseJsonFileName}"
    downloadFrom artifactoryPath:artifactoryPath, destinationPath:'', artifactoryUrl:config.artifactoryUrl, credentialsId:config.credentialsId

    def releaseJson = readJSON file: releaseJsonFileName
    log.info "Latest release version: ${releaseJson.version}"
    return releaseJson.version
}
*/
/**
 * Uploads the given artifactory path to the given destination path.
 * Pass following parameters when calling uploadTo:
 *  @param config.artifactoryPath - artifactory path
 *  @param config.destinationPath - our build directory
 *  @param config.artifactoryUrl - artifactory url
 *  @param config.credentialsId - artifactory credentials id stored in jenkins
 */
/*def uploadTo(Map config=[:]) {
    try {
        def artifactoryServer = Artifactory.newServer url: config.artifactoryUrl, credentialsId: config.credentialsId
        def uploadSpec = """{
            "files": [
                {
                    "pattern": "${config.artifactoryPath}",
                    "target": "${config.destinationPath}"
                }
            ]
        }"""
        artifactoryServer.upload(uploadSpec)
        log.info "Successfully downloaded ${uploadSpec} to artifactory"
    } catch (Exception e) {
        log.error "Caught exception while uploading to Artifactory: ${e}"
        throw e
    }
}*/