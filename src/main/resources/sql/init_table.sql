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
