/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wdk.file;

import java.io.IOException;
import wdk.data.Draft;

/**
 *
 * @author Leon
 */
public interface DraftFileManager 
{
    public void saveDraft(Draft draftToSave) throws IOException;
    public void loadDraft(Draft draftToLoad) throws IOException;
    
}
