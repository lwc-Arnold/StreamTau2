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

package com.zetyun.streamtau.manager.pea.app;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.zetyun.streamtau.core.pea.PeaId;
import com.zetyun.streamtau.manager.pea.PeaType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@JsonTypeName("JavaJarApp")
@EqualsAndHashCode(callSuper = true)
public class JavaJarApp extends App implements WithSingleServer {
    @Schema(
        description = "The id of the JarFile asset to run.",
        example = "01B2752D-28D6-4B1C-80BD-4B7A0531539C"
    )
    @JsonProperty("jarFile")
    @Getter
    @Setter
    @PeaId
    @PeaType("JarFile")
    private String jarFile;
    @Schema(
        description = "The id of the executor to run this app.",
        example = "44ED169D-F7FC-4720-8EDA-BADA8E3E006E"
    )
    @JsonProperty("server")
    @Getter
    @Setter
    @PeaId
    @PeaType("Executor")
    private String server;
}
