/*
 * DBeaver - Universal Database Manager
 * Copyright (C) 2010-2023 DBeaver Corp and others
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

package org.jkiss.dbeaver.ui.editors.sql.registry;

import org.eclipse.core.runtime.IConfigurationElement;
import org.eclipse.core.runtime.IExtensionRegistry;
import org.eclipse.core.runtime.Platform;
import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;

import java.util.ArrayList;
import java.util.List;

/**
 * SQLConverterRegistry
 */
public class SQLConverterRegistry {

    public static final String EXTENSION_ID = "org.jkiss.dbeaver.sql.covert"; //NON-NLS-1 //$NON-NLS-1$

    private static final String TAG_TARGET = "target"; //NON-NLS-1

    private static SQLConverterRegistry instance;

    public synchronized static SQLConverterRegistry getInstance() {
        if (instance == null) {
            instance = new SQLConverterRegistry(Platform.getExtensionRegistry());
        }
        return instance;
    }

    private List<SQLTargetConverterDescriptor> targetConverters = new ArrayList<>();

    private SQLConverterRegistry(IExtensionRegistry registry)
    {
        // Load target converters
        IConfigurationElement[] panelElements = registry.getConfigurationElementsFor(EXTENSION_ID);
        for (IConfigurationElement ext : panelElements) {
            if (TAG_TARGET.equals(ext.getName())) {
                SQLTargetConverterDescriptor descriptor = new SQLTargetConverterDescriptor(ext);
                targetConverters.add(descriptor);
            }
        }
    }

    @NotNull
    public List<SQLTargetConverterDescriptor> getTargetConverters() {
        return targetConverters;
    }

    @Nullable
    public SQLTargetConverterDescriptor getTargetConverter(String converterId) {
        for (SQLTargetConverterDescriptor converter : targetConverters) {
            if (converter.getId().equals(converterId)) {
                return converter;
            }
        }
        return null;
    }

}
