CREATE TABLE `user_info` (
`id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
`open_id` VARCHAR ( 50 ) COMMENT 'openId',
`phone_number` VARCHAR ( 50 ) COMMENT '电话号码',
`nick_name` VARCHAR ( 50 ) COMMENT '昵称',
`password` VARCHAR ( 50 ) COMMENT '密码',
`avatar_url` VARCHAR ( 150 ) COMMENT '头像url',
`create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
`update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
PRIMARY KEY ( `id` ),
UNIQUE KEY `uk_open_id` ( `open_id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户信息';


CREATE TABLE `intelligent_test_scale`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类别',
  `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
  `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标语',
  `is_free` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否收费',
  `avatar_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片url',
  `cnt` bigint(20)  DEFAULT 0 COMMENT '查看人数',
  `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '智能测评量表' ROW_FORMAT = Dynamic;



INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	1	, '情绪情感篇', '躯体化症状（SCL-90）自评测', '你的身体有哪些不适感？', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	2	, '情绪情感篇', '焦虑情绪自评测（SAS）', '别让焦虑毁了你的人生', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	3	, '情绪情感篇', '抑郁情绪自评测（SDS）', '你的抑郁情绪“晴雨表”', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	4	, '情绪情感篇', '焦虑状态自评测（S-AI）', '焦虑状态自我评估', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	5	, '情绪情感篇', '贝克抑郁自评测（BDI）', '测测你的抑郁有多深？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	6	, '情绪情感篇', '抑郁度（SCL-90）自评测', '抑郁程度知多少', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	7	, '情绪情感篇', '社交焦虑自评测（修订版）', '你的社交焦虑有多少？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	8	, '情绪情感篇', '社交回避及苦恼自评测（SAD）', '一个人为什么回避社交？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	9	, '情绪情感篇', '当众交流恐惧自评测（PRCA-24）', '你为什么当众交流会恐惧？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	10	, '情绪情感篇', '交往焦虑自评测（IAS）', '为什么我和谁待着都不舒服？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	11	, '情绪情感篇', '在婚姻里，你有安全感吗？', '安全感是彼此信任的基础', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	12	, '情绪情感篇', '家庭亲密度自评测（FACESII-CV）', '为什么有的家庭如此温暖？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	13	, '情绪情感篇', '夫妻冲突解决方式自评测（ENRICH）', '你和TA是如何化解冲突的？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	14	, '情绪情感篇', '你的性生活还和谐吗？', '从性了解亲密关系', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	15	, '人际关系篇', '你能容纳他人吗？', '宽容，是智慧的开始', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	16	, '人际关系篇', '你信赖他人吗？', '互相信赖，彼此靠近', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	17	, '人际关系篇', '测测你的人际魅力指数', '在他人眼中你的人际魅力有多大？', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	18	, '人际关系篇', '人际关系敏感（SCL-90）自评测', '你对人际关系有多敏感？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	19	, '人际关系篇', '孤独自评测（UCLA）', '如何高质量地独处？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	20	, '人际关系篇', '社交技能自评测（TSBI）', '你的社交手腕“知多少”', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	21	, '人际关系篇', '你给人的第一印象如何？', '第一印象决定了一半', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	22	, '人际关系篇', '社会支持评定（SSRS）自评测', '测一测都有谁在支持你前行', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	23	, '人际关系篇', '惧怕否定评价自评测（FNE）', '你有多害怕别人的否定？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	24	, '人际关系篇', '简易应对方式自评测（SCSQ）', '面对突发事件，你有哪些应对方式？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	25	, '人格特质篇', '八道题测试你的心理素质', '你的内心够强大吗？', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	26	, '人格特质篇', '讨好型人格自评测', '你会讨好他人吗？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	27	, '人格特质篇', '人生意义感自测评（C-MLQ）', '一个人的人生意义到底是什么？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	28	, '人格特质篇', '完美主义人格则评测', '你的完美主义有多严重？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	29	, '人格特质篇', '羞怯自评测（修订版）', '在他人眼中，你会羞怯吗？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	30	, '人格特质篇', '自我和谐自评测（SCCS）', '你的自我和谐吗？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	31	, '人格特质篇', '你是内控型还是外控型？（IPC-I）', '你的控制点在哪里？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	32	, '人格特质篇', '成人自尊量表（RSES）', '测测你的自尊水平有多高？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	33	, '人格特质篇', '安全感自评测（SQ）', '你是否缺乏安全感？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	34	, '人格特质篇', '核心自我评价量表（CSES）', '你认同自己吗？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	35	, '认知思维篇', '你拖延到什么程度了？', '拖延，是时间的小偷', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	36	, '认知思维篇', '标准情商（EQ）自评测', '情商高低，一测便知', '免费', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	37	, '认知思维篇', '强迫症状（SCL-90）自评测', '你有强迫症吗？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	38	, '认知思维篇', 'Yale-Brown强迫症状自评测', '你有强迫症吗？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	39	, '认知思维篇', '自动思维自评测（ATQ）', '如何摆脱固化的思维模式？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	40	, '认知思维篇', '职业延迟满足能力自评测（ODGS）', '为什么延迟满足使人更容易成功？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	41	, '认知思维篇', '你的职业心理年龄自评测', '你的职业心理年龄多大了？', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO intelligent_test_scale(`id`, `type`, `name`, `title`, `is_free`, `avatar_url`, `create_time`, `update_time`) VALUES (	42	, '认知思维篇', 'IT企业员工工作成瘾自评测', '适合“知识型”职场打工人', '9.8 原价 19.9', '', CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

update intelligent_test_scale set is_free='5.25 原价 19.8' where is_free <> '免费';


CREATE TABLE `meditation_music`  (
   `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
   `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类别',
   `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
   `title` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '标语',
   `is_free` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '是否收费',
   `duration` tinyint(20)  DEFAULT 0 COMMENT '时长',
   `avatar_url` varchar(150) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '图片url',
   `mp3_name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'mp3名称',
   `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
   `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
   PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '冥想音乐' ROW_FORMAT = Dynamic;




INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	1	, '大自然篇', '春雨绵绵', '淅淅沥沥，细雨如丝', '免费', 	4	, '','春雨绵绵',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	2	, '大自然篇', '乡村清晨', '鸡犬相闻', '免费', 	5	, '','乡村清晨',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	3	, '大自然篇', '童年仲夏夜', '回到那年的夏天', '免费', 	4	, '','童年仲夏夜',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	4	, '大自然篇', '悦耳鸟叫', '两个黄鹂鸣翠柳', '免费', 	2	, '','悦耳鸟叫',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	5	, '大自然篇', '潮涨潮落', '听海', '免费', 	3	, '','潮涨潮落',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	6	, '大自然篇', '空灵山谷', '秘境流水', '免费', 	10	, '','空灵山谷',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	7	, '大自然篇', '山间小溪', '清泉石上流', '免费', 	6	, '','山间小溪',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	8	, '大自然篇', '睡前颂钵', '安神助眠 神奇疗愈', '免费', 	10	, '','睡前颂钵',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	9	, '大自然篇', '围炉篝火', '木柴的叹息', '免费', 	4	, '','围炉篝火',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	10	, '大自然篇', '溪边鸟鸣', '鸟鸣山涧', '免费', 	2	, '','溪边鸟鸣',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	11	, '身体扫描篇', '身体扫描（基础）', '打开觉知，感受自我', '免费', 	4	, '','身体扫描（心芽之道·正念练习女声音频标准）',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	12	, '身体扫描篇', '身体扫描（入门）', '打开觉知，感受自我', '免费', 	6	, '','身体扫描（心芽之道·正念练习女声音频入门）',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	13	, '身体扫描篇', '身体扫描（进阶）', '打开觉知，感受自我', '免费', 	7	, '','身体扫描（心芽之道·正念练习女声音频进阶）',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	14	, '呼吸觉察篇', '鼻腔呼吸法', '专注一呼一吸', '免费', 	4	, '','鼻式呼吸法（心芽之道·正念练习女声音频）',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	15	, '呼吸觉察篇', '腹式呼吸法', '专注一呼一吸', '免费', 	6	, '','腹式呼吸法（心芽之道·正念练习女声音频）',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	16	, '呼吸觉察篇', '无选择觉察', '无特定对象的开放式觉知', '9.8 原价 19.9', 	6	, '','无选择觉察（心芽之道·正念练习女声音频）',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	17	, '呼吸觉察篇', '全然抵达当下', '只是感受，就够了', '9.8 原价 19.9', 	7	, '','全然抵达当下',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	18	, '职场篇', '职场减压', '压力是个小问题', '9.8 原价 19.9', 	7	, '','职场减压',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	19	, '职场篇', '职场焦虑缓解', '拥抱焦虑，感受情绪', '9.8 原价 19.9', 	7	, '','职场焦虑缓解',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	20	, '职场篇', '摆脱抑郁情绪', '直面抑郁，感受情绪', '9.8 原价 19.9', 	6	, '','摆脱工作中的抑郁情绪',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	21	, '日常生活篇', '正念吃葡萄', '让饮食，有滋有味', '9.8 原价 19.9', 	6	, '','正念吃葡萄',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	22	, '日常生活篇', '正念做家务', '家务，原来如此有趣', '9.8 原价 19.9', 	7	, '','正念做家务',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	23	, '日常生活篇', '正念走路', '别着急赶路，试着去感受路', '9.8 原价 19.9', 	6	, '','正念行走',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);
INSERT INTO meditation_music(`id`, `type`, `name`, `title`, `is_free`, `duration`, `avatar_url`,`mp3_name`, `create_time`, `update_time`) VALUES (	24	, '日常生活篇', '正念运动', '不一样的运动方式', '9.8 原价 19.9', 	5	, '','正念运动',CURRENT_TIMESTAMP, CURRENT_TIMESTAMP);

update meditation_music set is_free='5.25 原价 19.8' where is_free <> '免费';


CREATE TABLE `note3`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户open_id唯一的',
    `mood` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '自动化负性观念',
    `analysis_type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '不合理认知分析类型',
    `analysis` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '不合理认知分析类型名',
    `analysis_detail` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '不合理认知分析详情',
    `defend` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '我的理性回应',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8mb4 COLLATE = utf8mb4_general_ci COMMENT = '三栏笔记' ROW_FORMAT = Dynamic;


CREATE TABLE `user_info_category_cnt` (
    `id` BIGINT NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id` VARCHAR ( 50 ) COMMENT 'openId',
    `category` VARCHAR ( 50 ) COMMENT '类型',
    `category_id` VARCHAR ( 50 ) COMMENT '类型唯一id',
    `cnt` BIGINT COMMENT '次数',
    `create_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY ( `id` ),
    UNIQUE KEY `uk_open_id_cate_cate_id` ( `open_id`,`category`,`category_id` )
) ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '用户使用次数表';


update intelligent_test_scale set cnt= 39 where id = 1 ;
update intelligent_test_scale set cnt= 185 where id = 2 ;
update intelligent_test_scale set cnt= 45 where id = 3 ;
update intelligent_test_scale set cnt= 42 where id = 4 ;
update intelligent_test_scale set cnt= 677 where id = 5 ;
update intelligent_test_scale set cnt= 20 where id = 6 ;
update intelligent_test_scale set cnt= 45 where id = 7 ;
update intelligent_test_scale set cnt= 192 where id = 8 ;
update intelligent_test_scale set cnt= 3634 where id = 9 ;
update intelligent_test_scale set cnt= 56 where id = 10 ;
update intelligent_test_scale set cnt= 497 where id = 11 ;
update intelligent_test_scale set cnt= 6763 where id = 12 ;
update intelligent_test_scale set cnt= 51 where id = 13 ;
update intelligent_test_scale set cnt= 68 where id = 14 ;
update intelligent_test_scale set cnt= 239 where id = 15 ;
update intelligent_test_scale set cnt= 54 where id = 16 ;
update intelligent_test_scale set cnt= 2578 where id = 17 ;
update intelligent_test_scale set cnt= 560 where id = 18 ;
update intelligent_test_scale set cnt= 11234 where id = 19 ;
update intelligent_test_scale set cnt= 58 where id = 20 ;
update intelligent_test_scale set cnt= 59 where id = 21 ;
update intelligent_test_scale set cnt= 60 where id = 22 ;
update intelligent_test_scale set cnt= 61 where id = 23 ;
update intelligent_test_scale set cnt= 62 where id = 24 ;
update intelligent_test_scale set cnt= 63 where id = 25 ;
update intelligent_test_scale set cnt= 64 where id = 26 ;
update intelligent_test_scale set cnt= 65 where id = 27 ;
update intelligent_test_scale set cnt= 66 where id = 28 ;
update intelligent_test_scale set cnt= 67 where id = 29 ;
update intelligent_test_scale set cnt= 68 where id = 30 ;
update intelligent_test_scale set cnt= 69 where id = 31 ;
update intelligent_test_scale set cnt= 70 where id = 32 ;
update intelligent_test_scale set cnt= 71 where id = 33 ;
update intelligent_test_scale set cnt= 72 where id = 34 ;
update intelligent_test_scale set cnt= 73 where id = 35 ;
update intelligent_test_scale set cnt= 74 where id = 36 ;
update intelligent_test_scale set cnt= 75 where id = 37 ;
update intelligent_test_scale set cnt= 76 where id = 38 ;
update intelligent_test_scale set cnt= 77 where id = 39 ;
update intelligent_test_scale set cnt= 78 where id = 40 ;
update intelligent_test_scale set cnt= 79 where id = 41 ;
update intelligent_test_scale set cnt= 80 where id = 42 ;


alter table user_info add column is_member TINYINT ( 10 ) COMMENT '是否会员' not null default 0 after avatar_url;
alter table user_info add column not_member_msg_cnt BIGINT COMMENT '非会员每天消息数' DEFAULT 0  after is_member;


CREATE TABLE `mood_map`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户open_id唯一的',
    `mood` int(10) DEFAULT 0 COMMENT '心情(1快乐2惊讶3愤怒4悲伤5恐惧)',
    `mood_date` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL DEFAULT '1900-01-01' COMMENT '用户心情日期',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_open_id_mood_date` ( `open_id`,`mood_date` )
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '心情地图表';;

CREATE TABLE `psychologist`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户open_id唯一的',
    `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户名',
    `gender` int(2) DEFAULT 0 COMMENT '性别(0女1男)',
    `slogan` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '口号',
    `background_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '背景图',
    `avatar_url` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户头像图标',
    `experience_cnt` int(10) DEFAULT 0 COMMENT '经验人数',
    `response_rate` double DEFAULT 1 COMMENT '响应率',
    `applause_rate` double DEFAULT 1 COMMENT '好评率',
    `recommended_rate` double DEFAULT 1 COMMENT '推荐率',
    `person_introduce` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '个人介绍',
    `listen_style` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '倾听风格',
    `professional_qualification` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '专业资质',
    `expert_areas` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '擅长领域',
    `price` double DEFAULT 19.8 COMMENT '价格',
    `is_member` int(10) DEFAULT 0 COMMENT '是否是严选0否1是',
    `status` int(10)  DEFAULT 0 COMMENT '个人状态0喊他上线1服务中2找他聊',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_open_id` ( `open_id`)
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '心理咨询师表';


CREATE TABLE `conversation`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `psychologist_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '咨询师open_id',
    `client_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '来访者open_id',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_2id` ( `psychologist_id`,`client_id`)
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '聊天会话表';


CREATE TABLE `message`  (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `conversation_id` int(10) COMMENT '谈话唯一id',
     `is_sender_psychologist` TINYINT ( 10 ) COMMENT '发消息的是咨询师0否1是',
     `message` varchar(500) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '发的消息详情',
     `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
     PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '聊天详情表';


CREATE TABLE `pay_info` (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id` varchar(50) DEFAULT NULL COMMENT '用户open_id唯一的',
    `category` varchar(50) DEFAULT NULL COMMENT '大类，TYPE_TEST,TYPE_MEDITATION两种',
    `uni_id` varchar(50) DEFAULT NULL COMMENT '大类下的id唯一的',
    `amount` varchar(50) DEFAULT NULL COMMENT '价格',
    `trade_no` varchar(50) DEFAULT NULL COMMENT '订单号',
    `desc` varchar(200) DEFAULT NULL COMMENT '订单描述',
    `feedback` varchar(200) DEFAULT NULL COMMENT '订单评价',
    `create_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_openid_category_uniid` (`open_id`,`category`,`uni_id`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8mb4 ROW_FORMAT=DYNAMIC COMMENT='支付信息';


CREATE TABLE `muyu`  (
    `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
    `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户唯一open_id',
    `hit_date` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '敲击日',
    `hit_cnt` int(20)  DEFAULT 0 COMMENT '敲击数',
    `is_del` int(20)  DEFAULT 0 COMMENT '是否删除',
    `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE KEY `uk_openid_date` ( `open_id`,`hit_date`)
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '木鱼表';


CREATE TABLE `organization`  (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '机构名称',
     `member_cnt` int(20)  DEFAULT 0 COMMENT '敲击数',
     `expired_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '失效时间',
     `pay_type` int(20)  DEFAULT 0 COMMENT '支付类别,0成长中心，咨询师，音频都需要付费，1成长中心，咨询师需要付费，2成长中心需付费',
     `check_name_type` int(20)  DEFAULT 0 COMMENT '姓名检查类别,0无需检查，1需要检查',
     `is_del` int(20)  DEFAULT 0 COMMENT '是否删除',
     `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
     PRIMARY KEY (`id`) USING BTREE,
     UNIQUE KEY `uk_name` (`name`)
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '机构表';

alter table user_info add column organization_id bigint ( 20 ) COMMENT '机构id' not null default 0 after open_id;

alter table organization modify column pay_type int(20)  DEFAULT 0 COMMENT '支付类别,0成长中心，咨询师，音频都需要付费，1成长中心，咨询师需要付费，2成长中心需付费';


alter table user_info add column real_name varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '真实名称' after nick_name;


CREATE TABLE `growth_center`  (
     `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
     `type` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '类型',
     `name` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '名称',
     `duration` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '时长',
     `src` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '音频地址',
     `is_del` int(20)  DEFAULT 0 COMMENT '是否删除',
     `create_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
     `update_time` timestamp(0) NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '更新时间',
     PRIMARY KEY (`id`) USING BTREE
)ENGINE = INNODB DEFAULT CHARSET = utf8mb4 COMMENT = '成长中心表';

alter table growth_center add column `amount` varchar(50) DEFAULT NULL COMMENT '价格' after duration;

INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (1, 'job', '三个减压神器', '8:11', 0, '2025-01-06 22:27:57', '2025-01-06 22:27:57');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (2, 'job', '专注力训练', '8:26', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (3, 'job', '呼吸训练', '9:33', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (4, 'job', '想象放松', '8:12', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (5, 'job', '肌肉放松训练', '7:52', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (6, 'job', '肩颈放松', '10:31', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (7, 'job', '身心放松', '8:40', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (8, 'job', '静心放松', '7:20', 0, '2025-01-06 23:01:37', '2025-01-06 23:01:37');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (9, 'worry', '	全然抵达当下	', '	9:44	', 0, '2025-01-06 23:15:49', '2025-01-07 00:41:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (10, 'worry', '	尝试接纳自己	', '	7:49	', 0, '2025-01-06 23:15:49', '2025-01-07 00:41:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (11, 'worry', '	摆脱抑郁情绪	', '	7:43	', 0, '2025-01-06 23:15:49', '2025-01-07 00:41:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (12, 'worry', '	无选择觉察	', '	8:43	', 0, '2025-01-06 23:15:49', '2025-01-07 00:41:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (13, 'worry', '	直面焦虑漩涡	', '	9:39	', 0, '2025-01-06 23:15:49', '2025-01-07 00:41:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (14, 'worry', '	解离负面思维	', '	8:17	', 0, '2025-01-06 23:15:49', '2025-01-07 00:41:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (15, 'mood', '做情绪的主人', '7:49', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (16, 'mood', '利用你的情绪', '6:12', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (17, 'mood', '培养你的积极心态', '8:19', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (18, 'mood', '接纳你的情绪', '7:16', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (19, 'mood', '接纳你的消极心态.', '6:29', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (20, 'mood', '认识你的情绪', '10:40', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (21, 'mood', '让你的情绪自由流动', '8:35', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');
INSERT INTO `springboot_demo`.`growth_center`(`id`, `type`, `name`, `duration`, `is_del`, `create_time`, `update_time`) VALUES (22, 'mood', '释放你的情绪', '14:49', 0, '2025-01-06 23:29:18', '2025-01-06 23:29:18');






create table demo.survey
(
    id            bigint auto_increment comment 'id'
        primary key,
    survey_type   varchar(100)                                                      null comment '测评类型',
    survey_name   varchar(50) collate utf8mb4_general_ci                            null comment '测评名称',
    survey_desc   varchar(200) collate utf8mb4_general_ci default '0'               null comment '测评描述',
    survey_remark varchar(800) collate utf8mb4_general_ci default '0'               null comment '测评描述',
    src           varchar(200) collate utf8mb4_general_ci                           null comment '背景图片url',
    create_time   timestamp                               default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time   timestamp                               default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '测评表' charset = utf8mb4;

create table demo.survey_answer
(
    id          bigint auto_increment comment 'id'
        primary key,
    question_id bigint                                  not null comment '问题id',
    rank_id     bigint                                  not null comment '排名位置',
    score       bigint                                  not null comment '答案分数',
    answer_text varchar(200) collate utf8mb4_general_ci null comment '答案内容',
    create_time timestamp default CURRENT_TIMESTAMP     not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '答案表' charset = utf8mb4;

create table demo.survey_question
(
    id                 bigint auto_increment comment 'id'
        primary key,
    survey_id          bigint                                  not null comment '测评id',
    question_text      varchar(200) collate utf8mb4_general_ci null comment '问题内容',
    is_multiple_choice int       default 0                     not null comment '是否多选',
    is_required        int       default 1                     not null comment '是否必填',
    create_time        timestamp default CURRENT_TIMESTAMP     not null comment '创建时间',
    update_time        timestamp default CURRENT_TIMESTAMP     not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '问题表' charset = utf8mb4;

create table demo.survey_record
(
    id          bigint auto_increment comment 'id'
        primary key,
    open_id     bigint                              not null comment '用户微信编码',
    survey_id   bigint                              not null comment '测评id',
    score       bigint                              not null comment '答案分数',
    start_time  timestamp default CURRENT_TIMESTAMP not null comment '开始时间',
    end_time    timestamp default CURRENT_TIMESTAMP not null comment '结束时间',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户答题记录表' charset = utf8mb4;

create table demo.survey_record_detail
(
    id          bigint auto_increment comment 'id'
        primary key,
    record_id   bigint                              not null comment '测评记录id',
    question_id bigint                              not null comment '问题id',
    answer_id   bigint                              not null comment '答案id',
    answer_text bigint                              not null comment '答案内容',
    start_time  timestamp default CURRENT_TIMESTAMP not null comment '开始时间',
    end_time    timestamp default CURRENT_TIMESTAMP not null comment '结束时间',
    create_time timestamp default CURRENT_TIMESTAMP not null comment '创建时间',
    update_time timestamp default CURRENT_TIMESTAMP not null on update CURRENT_TIMESTAMP comment '更新时间'
)
    comment '用户答题记录详情表' charset = utf8mb4;

