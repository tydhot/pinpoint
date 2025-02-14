/*
 * Copyright 2019 NAVER Corp.
 *
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

package com.navercorp.pinpoint.profiler.plugin.filter;

import com.navercorp.pinpoint.profiler.plugin.PluginJar;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

/**
 * @author Woonduk Kang(emeroad)
 */
public class ImportPluginFilter implements PluginFilter {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private final Set<String> enablePluginIds;

    public ImportPluginFilter(List<String> pluginIds) {
        Objects.requireNonNull(pluginIds, "pluginIds");
        this.enablePluginIds = new HashSet<>(pluginIds);
    }

    @Override
    public boolean accept(PluginJar pluginJar) {
        final String pluginId = pluginJar.getPluginId();
        if (enablePluginIds.contains(pluginId)) {
            logger.info("Enable plugin : {}", pluginId);
            return ACCEPT;
        }
        return REJECT;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EnablePluginFilter{");
        sb.append("enablePluginIds=").append(enablePluginIds);
        sb.append('}');
        return sb.toString();
    }
}
