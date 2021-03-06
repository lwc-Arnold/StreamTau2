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

package com.zetyun.streamtau.manager.service.impl;

import com.zetyun.streamtau.manager.properties.LocalFileStorageProperties;
import com.zetyun.streamtau.manager.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import javax.annotation.Nonnull;

@Service
@ConditionalOnProperty(name = "streamtau.storage.type", havingValue = "local", matchIfMissing = true)
@EnableConfigurationProperties(LocalFileStorageProperties.class)
@Slf4j
public class LocalFileStorageService implements StorageService {
    private final Path root;

    @Autowired
    public LocalFileStorageService(@Nonnull LocalFileStorageProperties properties) {
        String dir = properties.getDir();
        if (dir == null || dir.isEmpty()) {
            root = Paths.get(System.getProperty("java.io.tmpdir"), "StreamTau");
        } else {
            root = Paths.get(dir).toAbsolutePath();
        }
    }

    @Override
    public String createFile(String extension) {
        return UUID.randomUUID().toString().replace('-', '_') + "." + extension;
    }

    @Override
    public void saveFile(String path, MultipartFile file) throws IOException {
        if (!Files.isDirectory(root)) {
            Files.createDirectory(root);
        }
        file.transferTo(root.resolve(path));
    }

    @Override
    public void copyFile(String path, String newPath) throws IOException {
        Files.copy(root.resolve(path), root.resolve(newPath));
    }

    @Override
    public String resolve(String path) {
        return root.resolve(path).toAbsolutePath().toString();
    }
}
