package com.jstarcraft.rns.model.collaborative.rating;

import java.util.Map;
import java.util.Properties;

import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;

import com.jstarcraft.ai.evaluate.Evaluator;
import com.jstarcraft.ai.evaluate.rating.MAEEvaluator;
import com.jstarcraft.ai.evaluate.rating.MPEEvaluator;
import com.jstarcraft.ai.evaluate.rating.MSEEvaluator;
import com.jstarcraft.core.utility.Configurator;
import com.jstarcraft.rns.model.collaborative.rating.GPLSAModel;
import com.jstarcraft.rns.task.RatingTask;

import it.unimi.dsi.fastutil.objects.Object2FloatSortedMap;

public class GPLSAModelTestCase {

    @Test
    public void testRecommender() throws Exception {
        Properties keyValues = new Properties();
        keyValues.load(this.getClass().getResourceAsStream("/data.properties"));
        keyValues.load(this.getClass().getResourceAsStream("/recommend/collaborative/rating/gplsa-test.properties"));
        Configurator configuration = new Configurator(keyValues);
        RatingTask job = new RatingTask(GPLSAModel.class, configuration);
        Object2FloatSortedMap<Class<? extends Evaluator>> measures = job.execute();
        Assert.assertEquals(0.6415139F, measures.getFloat(MAEEvaluator.class), 0F);
        Assert.assertEquals(0.99014086F, measures.getFloat(MPEEvaluator.class), 0F);
        Assert.assertEquals(0.6969512F, measures.getFloat(MSEEvaluator.class), 0F);
    }

}