package org.checkout.ioc

import org.checkout.IStepExecutor

interface IContext {
    IStepExecutor getStepExecutor()
}
