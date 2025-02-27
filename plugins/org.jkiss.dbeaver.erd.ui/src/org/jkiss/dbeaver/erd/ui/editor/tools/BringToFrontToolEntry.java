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
package org.jkiss.dbeaver.erd.ui.editor.tools;

import org.eclipse.gef3.DefaultEditDomain;
import org.eclipse.gef3.GraphicalViewer;
import org.eclipse.gef3.SharedImages;
import org.eclipse.gef3.palette.ToolEntry;
import org.eclipse.gef3.tools.SelectionTool;
import org.jkiss.dbeaver.erd.ui.editor.ERDEditorPart;
import org.jkiss.dbeaver.erd.ui.part.NodePart;

public class BringToFrontToolEntry extends ToolEntry {

    public BringToFrontToolEntry() {
        super("Bring to front", "Bring figure to front", SharedImages.DESC_SELECTION_TOOL_16, SharedImages.DESC_SELECTION_TOOL_24, ToolBringToFront.class);

        this.setUserModificationPermission(1);
    }

    public static class ToolBringToFront extends SelectionTool {

        @Override
        public void activate()
        {
            //ERDGraphicalViewer.this.reveal(part);
            DefaultEditDomain editDomain = (DefaultEditDomain) getDomain();
            final ERDEditorPart editorPart = (ERDEditorPart)editDomain.getEditorPart();
            final GraphicalViewer viewer = editorPart.getViewer();
            for (Object child : editorPart.getDiagramPart().getChildren()) {
                if (child instanceof NodePart) {
                    bringToFront((NodePart)child);
                }
            }
            super.activate();
        }

        private void bringToFront(NodePart part) {

        }
    }

}
