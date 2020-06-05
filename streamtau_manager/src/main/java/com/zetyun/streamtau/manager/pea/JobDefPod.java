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

package com.zetyun.streamtau.manager.pea;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.zetyun.streamtau.manager.pea.generic.MapPod;
import lombok.Getter;

import java.io.IOException;
import java.util.Map;

public class JobDefPod extends MapPod<String, String, AssetPea> {
    @JsonProperty("appId")
    @Getter
    private final String appId;

    public JobDefPod(String appId) {
        super();
        this.appId = appId;
    }

    @JsonCreator
    public JobDefPod(
        @JsonProperty(value = "appId", required = true) String appId,
        @JsonProperty(value = "map", required = true) Map<String, AssetPea> peaMap
    ) {
        super(peaMap);
        this.appId = appId;
    }

    public static JobDefPod fromJobDefinition(String json) throws IOException {
        return PeaParser.JSON.parse(json, JobDefPod.class);
    }

    @JsonProperty(value = "map", access = JsonProperty.Access.READ_ONLY)
    public Map<String, AssetPea> getPeaMap() {
        return peaMap;
    }

    @JsonIgnore
    public AssetPea getApp() {
        return peaMap.get(appId);
    }

    public String toJobDefinition() throws IOException {
        return PeaParser.JSON.stringShowAll(this);
    }
}