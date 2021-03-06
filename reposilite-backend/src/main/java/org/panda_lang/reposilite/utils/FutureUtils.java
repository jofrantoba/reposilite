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

package org.panda_lang.reposilite.utils;

import org.jetbrains.annotations.Nullable;
import org.panda_lang.reposilite.Reposilite;
import org.panda_lang.utilities.commons.function.ThrowingFunction;
import org.panda_lang.utilities.commons.function.ThrowingRunnable;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;

public final class FutureUtils {

    private FutureUtils() { }

    public static <T, E extends Exception> CompletableFuture<T> submit(Reposilite reposilite, ExecutorService service, ThrowingFunction<CompletableFuture<T>, ?, E> futureConsumer) {
        CompletableFuture<T> completableFuture = new CompletableFuture<>();
        service.submit(() -> run(reposilite, () -> futureConsumer.apply(completableFuture)));
        return completableFuture;
    }

    public static <E extends Exception> Runnable ofChecked(Reposilite reposilite, ThrowingRunnable<E> runnable) {
        return () -> run(reposilite, runnable);
    }

    public static <E extends Exception> void executeChecked(Reposilite reposilite, ExecutorService service, ThrowingRunnable<E> runnable) {
        service.execute(() -> run(reposilite, runnable));
    }

    private static void run(@Nullable Reposilite reposilite, ThrowingRunnable<?> runnable) {
        try {
            runnable.run();
        } catch (Exception e) {
            if (reposilite != null) {
                reposilite.throwException("Exception occurred during the task execution", e);
            }
            else {
                e.printStackTrace();
            }
        }
    }

}
