package com.jstarcraft.rns.model.collaborative.ranking;

import java.util.Map;
import java.util.Properties;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.jstarcraft.ai.evaluate.Evaluator;
import com.jstarcraft.ai.evaluate.ranking.AUCEvaluator;
import com.jstarcraft.ai.evaluate.ranking.MAPEvaluator;
import com.jstarcraft.ai.evaluate.ranking.MRREvaluator;
import com.jstarcraft.ai.evaluate.ranking.NDCGEvaluator;
import com.jstarcraft.ai.evaluate.ranking.NoveltyEvaluator;
import com.jstarcraft.ai.evaluate.ranking.PrecisionEvaluator;
import com.jstarcraft.ai.evaluate.ranking.RecallEvaluator;
import com.jstarcraft.core.utility.Configurator;
import com.jstarcraft.rns.model.collaborative.ranking.SLIMModel;
import com.jstarcraft.rns.task.RankingTask;

import it.unimi.dsi.fastutil.objects.Object2FloatSortedMap;

public class SLIMModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/recommend/collaborative/ranking/slim-test.properties"));
        Configurator configuration = new Configurator(keyValues);
        RankingTask job = new RankingTask(SLIMModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(0.93500406F, measures.getFloat(AUCEvaluator.class), 0F);
        Assert.assertEquals(0.4998087F, measures.getFloat(MAPEvaluator.class), 0F);
        Assert.assertEquals(0.6658752F, measures.getFloat(MRREvaluator.class), 0F);
        Assert.assertEquals(0.59335566F, measures.getFloat(NDCGEvaluator.class), 0F);
        Assert.assertEquals(18.261118F, measures.getFloat(NoveltyEvaluator.class), 0F);
        Assert.assertEquals(0.3593051F, measures.getFloat(PrecisionEvaluator.class), 0F);
        Assert.assertEquals(0.6413308F, measures.getFloat(RecallEvaluator.class), 0F);
    }

}