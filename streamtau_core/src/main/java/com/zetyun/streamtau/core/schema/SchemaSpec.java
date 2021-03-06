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

package com.zetyun.streamtau.core.schema;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.zetyun.streamtau.runtime.schema.RtSchema;
import lombok.Getter;

@JsonTypeInfo(
    use = JsonTypeInfo.Id.NAME,
    property = "type",
    visible = true
)
@JsonSubTypes({
    @JsonSubTypes.Type(SchemaSpecInteger.class),
    @JsonSubTypes.Type(SchemaSpecNumber.class),
    @JsonSubTypes.Type(SchemaSpecString.class),
    @JsonSubTypes.Type(SchemaSpecBoolean.class),
    @JsonSubTypes.Type(SchemaSpecObject.class),
    @JsonSubTypes.Type(SchemaSpecArray.class),
})
public abstract class SchemaSpec {
    @JsonProperty(value = "type", access = JsonProperty.Access.WRITE_ONLY)
    @Getter
    private Types type;

    public abstract RtSchema createRtSchema();
}
