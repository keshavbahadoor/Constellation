package services.resource;

import com.badlogic.gdx.Gdx;
import com.uwsoft.editor.renderer.data.ProjectInfoVO;
import com.uwsoft.editor.renderer.data.ResolutionEntryVO;
import com.uwsoft.editor.renderer.resources.ResourceManager;

/**
 * Created by Keshav on 3/30/2015.
 */
public class CustomResourceManager extends ResourceManager
{
    public ResolutionEntryVO currentResolution;
    public float stageWidth;

    public void initCustomResources()
    {
        // load project configuration file
        this.loadProjectVO();

        // figure out current resolution
        currentResolution = this.getBestResolutionMatch(this.getProjectVO());

        stageWidth = Gdx.graphics.getHeight() / currentResolution.height * Gdx.graphics.getWidth();

        // set working resolution - init only loads these particular resolution resources
        this.setWorkingResolution(currentResolution.name);

        // loads all needed assets into memory
        this.initAllResources();
    }

    /**
     * Finds the closest match from the available texture resolutions generated.
     * These are closest in size to current screen size. Comparison is by screen height
     *
     * @param projectInfoVO
     * @return
     */
    public ResolutionEntryVO getBestResolutionMatch(ProjectInfoVO projectInfoVO)
    {
        float newDeltaSize = 0.0F;
        float deltaSize = Math.abs(projectInfoVO.originalResolution.height - Gdx.graphics.getHeight());
        ResolutionEntryVO result = projectInfoVO.originalResolution;

        for (int i=0; i< projectInfoVO.resolutions.size(); i++)
        {
            newDeltaSize = Math.abs(projectInfoVO.resolutions.get(i).height - Gdx.graphics.getHeight());
            if (deltaSize > newDeltaSize)
            {
                deltaSize = newDeltaSize;
                result = projectInfoVO.resolutions.get(i);
            }
        }//end for
        return result;
    }
}
