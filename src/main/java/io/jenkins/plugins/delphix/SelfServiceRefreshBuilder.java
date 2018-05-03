/**
 * Copyright (c) 2018 by Delphix. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package io.jenkins.plugins.delphix;

import java.io.IOException;

import org.kohsuke.stapler.DataBoundConstructor;
import org.kohsuke.stapler.QueryParameter;

import hudson.Extension;
import hudson.Launcher;
import hudson.model.AbstractBuild;
import hudson.model.BuildListener;
import hudson.util.ListBoxModel;

/**
 * Describes a Self Service Container Refresh build step for the Delphix plugin
 */
public class SelfServiceRefreshBuilder extends SelfServiceBuilder {

    @DataBoundConstructor
    public SelfServiceRefreshBuilder(String delphixEngine, String delphixEnvironment) {
        super(delphixEngine, delphixEnvironment);
    }

    /**
     * Run the Refresh Job
     *
     * @param  build                AbstractBuild
     * @param  launcher             Launcher
     * @param  listener             BuildListener
     *
     * @return
     *
     * @throws IOException
     * @throws InterruptedException
     */
    @Override
    public boolean perform(final AbstractBuild<?, ?> build, Launcher launcher, final BuildListener listener)
            throws IOException, InterruptedException {
        return super.perform(build, listener, DelphixEngine.SelfServiceOperationType.REFRESH);
    }

    @Extension
    public static final class RefreshDescriptor extends SelfServiceDescriptor {

        /**
         * Add containers to drop down for Refresh action
         */
        public ListBoxModel doFillDelphixEngineItems() {
            return super.doFillDelphixEngineItems();
        }

        /**
         * Add containers to drop down for Refresh action
         */
        public ListBoxModel doFillDelphixEnvironmentItems(@QueryParameter String delphixEngine) {
            return super.doFillDelphixSelfServiceItems(delphixEngine);
        }

        /**
         * Name to display for build step
         */
        @Override
        public String getDisplayName() {
            return Messages.getMessage(Messages.SELFSERVICE_REFRESH_OPERATION);
        }
    }
}
