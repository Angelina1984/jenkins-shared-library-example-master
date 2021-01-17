package org.checkout.build;

import org.checkout.IStepExecutor;
import org.checkout.ioc.ContextRegistry;
import org.checkout.ioc.IContext;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ArtifactoryExecutorTest {

    private IContext _context;
    private IStepExecutor _steps;
    String artifactoryPath = "some/path";
    String artifactoryUrl = "https://art-bobcat.com/artifactory";
    String credentialsId = "svc_d_artifactory";
    String destinationPath = "dist/123/";

    @Before
    public void setup() {
        _context = mock(IContext.class);
        _steps = mock(IStepExecutor.class);

        when(_context.getStepExecutor()).thenReturn(_steps);

        ContextRegistry.registerContext(_context);
    }

    @Test
    public void testDownloadFromArtifactory_withDestinationPath() {
        // prepare
        ArtifactoryExecutor artifactory = new ArtifactoryExecutor(artifactoryPath,artifactoryUrl,credentialsId);
        // execute
        artifactory.downloadFromArtifactory(destinationPath);
        verify(_steps, times(2)).sh(anyString());

    }

    @Test
    public void testDownloadFromArtifactory_noDestinationPath() {
        ArtifactoryExecutor artifactory = new ArtifactoryExecutor(artifactoryPath,artifactoryUrl,credentialsId);
        artifactory.downloadFromArtifactory("");
        verify(_steps, never()).sh(anyString());
     }
}
