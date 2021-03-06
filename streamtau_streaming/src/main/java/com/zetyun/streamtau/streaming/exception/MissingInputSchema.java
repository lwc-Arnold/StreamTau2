/*
 * Copyright 2020 Zetyun
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

package com.zetyun.streamtau.streaming.exception;

import com.zetyun.streamtau.streaming.model.Operator;
import lombok.Getter;

import javax.annotation.Nonnull;

public class MissingInputSchema extends RuntimeException {
    private static final long serialVersionUID = -9144974233738425175L;

    @Getter
    private final Operator operator;

    public MissingInputSchema(@Nonnull Operator operator) {
        super(
            "Must specify input schema for operator \"" + operator.getName() + "\"."
        );
        this.operator = operator;
    }
}
