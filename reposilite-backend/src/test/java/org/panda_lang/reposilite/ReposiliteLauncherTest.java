/*
 * Copyright (c) 2020 Dzikoysk
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.panda_lang.reposilite;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ReposiliteLauncherTest {

    @TempDir
    File workingDirectory;

    @Test
    void shouldPrintVersion() {
        ReposiliteLauncher.create("--version");
        assertTrue(ReposiliteWriter.contains(ReposiliteConstants.VERSION));
    }

    @Test
    void shouldPrintHelp() {
        ReposiliteLauncher.create("--help");
        assertTrue(ReposiliteWriter.contains("Commands"));
    }

    @Test
    void shouldReturnReposilite() {
        assertTrue(ReposiliteLauncher.create("-wd=" + workingDirectory.getAbsolutePath()).isPresent());
    }

}