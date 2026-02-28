/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.apache.commons.collections4.sequence;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class ReplacementsFinderTest {

    @Test
    public void testVisitKeepCommandTriggersHandler() {
        final int[] capturedSkipped = new int[1];

        final ReplacementsHandler<String> handler = new ReplacementsHandler<String>() {
            @Override
            public void handleReplacement(int skipped, List<String> from, List<String> to) {
                capturedSkipped[0] = skipped;
            }
        };

        final ReplacementsFinder<String> finder = new ReplacementsFinder<>(handler);

        finder.visitKeepCommand("A");
        finder.visitKeepCommand("B");
        finder.visitInsertCommand("C");
        finder.visitKeepCommand("D");

        assertEquals("Handler not triggered", 2, capturedSkipped[0]);
    }
}
