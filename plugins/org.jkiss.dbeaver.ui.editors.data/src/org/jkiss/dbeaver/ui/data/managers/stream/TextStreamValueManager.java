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
package org.jkiss.dbeaver.ui.data.managers.stream;

import org.eclipse.ui.IEditorPart;
import org.jkiss.code.NotNull;
import org.jkiss.code.Nullable;
import org.jkiss.dbeaver.DBException;
import org.jkiss.dbeaver.Log;
import org.jkiss.dbeaver.model.data.DBDContent;
import org.jkiss.dbeaver.model.runtime.DBRProgressMonitor;
import org.jkiss.dbeaver.model.struct.DBSTypedObject;
import org.jkiss.dbeaver.ui.data.IStreamValueEditor;
import org.jkiss.dbeaver.ui.data.IStreamValueManager;
import org.jkiss.dbeaver.ui.data.IValueController;
import org.jkiss.dbeaver.utils.ContentUtils;

/**
 * Text editor manager
 */
public class TextStreamValueManager implements IStreamValueManager {

    private static final Log log = Log.getLog(TextStreamValueManager.class);

    @Override
    public MatchType matchesTo(@NotNull DBRProgressMonitor monitor, @NotNull DBSTypedObject attribute, @Nullable DBDContent value) {
        // Applies to text values
        return ContentUtils.isTextContent(value) ? MatchType.DEFAULT : MatchType.APPLIES;
    }

    @Override
    public IStreamValueEditor createPanelEditor(@NotNull final IValueController controller)
        throws DBException
    {
        return new TextPanelEditor();
    }

    @Override
    public IEditorPart createEditorPart(@NotNull IValueController controller) {
        return new TextEditorPart();
    }

}
