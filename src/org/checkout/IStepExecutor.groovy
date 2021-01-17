package org.checkout

/**
 * Interface for calling any necessary Jenkins steps. This will be mocked in unit tests.
 */
interface IStepExecutor {
    int sh(String command)
    void error(String message)
    // void Artifactory() // how can I add Artifactory?
}
