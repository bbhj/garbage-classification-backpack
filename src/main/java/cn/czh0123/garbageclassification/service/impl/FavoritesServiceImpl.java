package cn.czh0123.garbageclassification.service.impl;

import cn.czh0123.garbageclassification.pojo.domain.Favorites;
import cn.czh0123.garbageclassification.exception.BusinessException;
import cn.czh0123.garbageclassification.mapper.FavoritesMapper;
import cn.czh0123.garbageclassification.mongo.domain.Question;
import cn.czh0123.garbageclassification.mongo.repository.QuestionRepository;
import cn.czh0123.garbageclassification.pojo.vo.FavVo;
import cn.czh0123.garbageclassification.query.FavoritesQuery;
import cn.czh0123.garbageclassification.service.IFavoritesService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 坐标服务接口实现
 */
@Service
@Transactional
public class FavoritesServiceImpl extends ServiceImpl<FavoritesMapper, Favorites> implements IFavoritesService {

    @Autowired
    private QuestionRepository questionRepository;

    @Override
    public IPage<Favorites> queryPage(FavoritesQuery qo) {
        IPage<Favorites> page = new Page<>(qo.getCurrentPage(), qo.getPageSize());
        QueryWrapper<Favorites> wrapper = Wrappers.<Favorites>query();
        return super.page(page, wrapper);
    }

    @Override
    public Integer updateStatus(Favorites favorites) {
        if (StringUtils.isEmpty(questionRepository.findQuestionById(favorites.getQuestionId())))
            throw new BusinessException(500, "该题目不存在");
        QueryWrapper<Favorites> wrapper = new QueryWrapper<>();
        wrapper.eq("user_id", favorites.getUserId()).eq("question_id", favorites.getQuestionId());
        if (StringUtils.isEmpty(super.getOne(wrapper))) {
            super.save(favorites);
            return 1;
        } else {
            super.remove(wrapper);
            return 0;
        }
    }

    @Override
    public List<FavVo> getFav(Long userId) {
        List<FavVo> favVOS = new ArrayList<>();
        super.list(new QueryWrapper<Favorites>().eq("user_id", userId)).forEach(favorites -> {
            Question question = questionRepository.findQuestionById(favorites.getQuestionId());
            favVOS.add(new FavVo(question.getId(), question.getQuestion(), question.getOptions()
                    .get(question.getAnswer())));
        });
        return favVOS;
    }
}
