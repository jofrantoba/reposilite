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

import org.junit.jupiter.api.Test;
import org.panda_lang.utilities.commons.StringUtils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class ArrayUtilsTest {

    private static final String[] EMPTY = StringUtils.EMPTY_ARRAY;
    private static final String[] ARRAY = { "a", "b", "c" };

    @Test
    void getLatest() {
        assertNull(ArrayUtils.getFirst(EMPTY));
        assertEquals("a", ArrayUtils.getFirst(ARRAY));
    }

    @Test
    void getLast() {
        assertNull(ArrayUtils.getLast(EMPTY));
        assertEquals("c", ArrayUtils.getLast(ARRAY));
    }

}