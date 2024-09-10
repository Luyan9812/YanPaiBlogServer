-- 初始化类目
INSERT INTO `category` (`id`, `category_name`)
VALUES ('1', '后端'),
       ('2', '前端'),
       ('3', '大数据'),
       ('4', 'Android'),
       ('5', 'IOS'),
       ('6', '人工智能'),
       ('7', '开发工具'),
       ('8', '代码人生'),
       ('9', '阅读');


-- 初始化标签
INSERT INTO `tag` (`id`, `tag_name`, `category_id`)
VALUES ('1', 'Java', '1') ,
       ('2', 'Go', '1') ,
       ('3', '算法', '1') ,
       ('4', 'Python', '1') ,
       ('5', 'Spring Boot', '1') ,
       ('6', '面试', '1') ,
       ('7', 'MySQL', '1') ,
       ('8', '数据库', '1') ,
       ('9', 'Spring', '1') ,
       ('10', '架构', '1') ,
       ('11', 'LeetCode', '1') ,
       ('12', 'Redis', '1') ,
       ('13', '前端', '1') ,
       ('14', 'Linux', '1') ,
       ('15', 'JavaScript', '2') ,
       ('16', 'Vue.js', '2') ,
       ('17', 'React.js', '2') ,
       ('18', 'CSS', '2') ,
       ('19', '面试', '2') ,
       ('20', 'TypeScript', '2') ,
       ('21', '后端', '2') ,
       ('22', 'Node.js', '2') ,
       ('23', '前端框架', '2') ,
       ('24', '算法', '2') ,
       ('25', 'Webpack', '2') ,
       ('26', '架构', '2') ,
       ('27', '微信小程序', '2') ,
       ('28', 'GitHub', '2') ,
       ('29', 'Kotlin', '4') ,
       ('30', 'Flutter', '4') ,
       ('31', '前端', '4') ,
       ('32', 'Java', '4') ,
       ('33', 'Android Jetpack', '4') ,
       ('34', 'APP', '4') ,
       ('35', 'Android Studio', '4') ,
       ('36', '源码', '4') ,
       ('37', '性能优化', '4') ,
       ('38', '面试', '4') ,
       ('39', '架构', '4') ,
       ('40', 'gradle', '4') ,
       ('41', '程序员', '4') ,
       ('42', 'Swift', '5') ,
       ('43', 'SwiftUI', '5') ,
       ('44', 'Flutter', '5') ,
       ('45', '算法', '5') ,
       ('46', '前端', '5') ,
       ('47', 'LeetCode', '5') ,
       ('48', 'Xcode', '5') ,
       ('49', 'Objective-C', '5') ,
       ('50', 'Mac', '5') ,
       ('51', 'WWDC', '5') ,
       ('52', '计算机视觉', '5') ,
       ('53', 'Apple', '5') ,
       ('54', '音视频开发', '5') ,
       ('55', '深度学习', '6') ,
       ('56', '算法', '6') ,
       ('57', '机器学习', '6') ,
       ('58', 'Python', '6') ,
       ('59', '后端', '6') ,
       ('60', '计算机视觉', '6') ,
       ('61', 'PyTorch', '6') ,
       ('62', 'NLP', '6') ,
       ('63', '数据分析', '6') ,
       ('64', '神经网络', '6') ,
       ('65', 'TensorFlow', '6') ,
       ('66', '数据可视化', '6') ,
       ('67', '程序员', '6') ,
       ('68', '数据挖掘', '6') ,
       ('69', '后端', '7') ,
       ('70', '前端', '7') ,
       ('71', '开源', '7') ,
       ('72', 'Git', '7') ,
       ('73', 'GitHub', '7') ,
       ('74', 'Linux', '7') ,
       ('75', '测试', '7') ,
       ('76', 'Python', '7') ,
       ('77', '数据库', '7') ,
       ('78', '设计', '7') ,
       ('79', '程序员', '7') ,
       ('80', 'JavaScript', '7') ,
       ('81', 'Unity3D', '7') ,
       ('82', 'Rust', '7') ,
       ('83', '大数据', '7') ,
       ('84', '程序员', '8') ,
       ('85', '前端', '8') ,
       ('86', '后端', '8') ,
       ('87', '算法', '8') ,
       ('88', 'JavaScript', '8') ,
       ('89', 'Java', '8') ,
       ('90', 'Python', '8') ,
       ('91', '架构', '8') ,
       ('92', '开源', '8') ,
       ('93', '面试', '8') ,
       ('94', '年终总结', '8') ,
       ('95', '大数据', '8') ,
       ('96', 'Linux', '8') ,
       ('97', '数据结构', '8') ,
       ('98', 'GitHub', '8') ,
       ('99', '云原生', '9') ,
       ('100', '笔记', '9') ,
       ('101', '程序员', '9') ,
       ('102', '前端', '9') ,
       ('103', '后端', '9') ,
       ('104', 'Serverless', '9') ,
       ('105', '开源', '9') ,
       ('106', '容器', '9') ,
       ('107', '微服务', '9') ,
       ('108', 'Java', '9') ,
       ('109', '产品', '9') ,
       ('110', '产品经理', '9') ,
       ('111', '算法', '9') ,
       ('112', 'RocketMQ', '9') ,
       ('113', '深度学习', '9') ,
       ('114', 'sqlite', '3') ,
       ('115', 'sql', '3') ,
       ('116', 'spark', '3') ,
       ('117', 'hive', '3') ,
       ('118', 'hbase', '3') ,
       ('119', 'hdfs', '3') ,
       ('120', 'redis', '3') ,
       ('121', 'hadoop', '3') ,
       ('122', 'rabbitmq', '3') ,
       ('123', 'postgresql', '3') ,
       ('124', '数据库', '3') ,
       ('125', '数据仓库', '3') ,
       ('126', '大数据', '3') ,
       ('127', 'oracle', '3') ,
       ('128', 'flink', '3') ,
       ('129', 'nosql', '3') ,
       ('130', 'mysql', '3') ,
       ('131', 'eureka', '3') ,
       ('132', 'mongodb', '3') ,
       ('133', 'zookeeper', '3') ,
       ('134', 'elasticsearch', '3') ,
       ('135', 'kafka', '3') ,
       ('136', 'json', '3');


-- 初始化文章表
INSERT INTO `article` (`id`, `user_id`, `title`, `picture`, `summary`, `category_id`, `status`)
VALUES (1, 1, '《Java编程的逻辑》书籍推荐', '', '好书推荐', 9, 2),
       (2, 1, '《算法竞赛入门经典》书籍推荐', '', '书籍推荐', 9, 2);


-- 初始化文章细节表
INSERT INTO `article_detail` (`id`, `article_id`, `content`)
VALUES (1, 1, '### 1. 作者简介\n&emsp;&emsp;马俊昌，邻家科技CTO和联合创始人，北京理工大学博士。曾就职于IBM，从事中间件、云计算架构和开发工作，在万普世纪负责移动广告平台大数据分析和算法优化工作。\n&emsp;&emsp;2014年联合创立邻家科技，主要产品“到位APP”是一个到家生活服务平台。十多年来，一直从事Java编程，积累了比较丰富的经验。平时喜欢读书，研究技术与创新，乐于分享编程心得，欢迎关注我的微信公众号“老马说编程”，和你一起探索编程本质。\n### 2. 内容简介\n<center>\n  <img src=\"/configImages/JavaLogic.png\" width=\"400\" >\n</center>\n\n&emsp;&emsp;Java专家撰写，力求透彻讲解每个知识点，逐步建立编程知识图谱。本书以Java语言为例，由基础概念入手，到背后实现原理与逻辑，再到应用实践，融会贯通。\n全书共六大部分，其要点如下：\n- 第一部分（第1～2章）讲解计算机程序的基本执行流程与元素，以及数据背后的二进制表示，帮读者掌握编程的基本概念。\n- 第二部分（第3～7章）讲解面向对象的编程原理与逻辑，涉及类、继承与多态、接口与抽象类、异常与常用基础类，让读者透彻了解Java的重要基础——面向对象。\n- 第三部分（第8～12章）介绍泛型与容器及其数据结构和算法，涵盖泛型、列表和队列、各种Map和Set、堆与优先级队列等。\n- 第四部分（第13～14章）介绍文件处理。涵盖文件的基本概念、二进制文件和字节流、文本文件和字符流、文件和目录操作，以及文件处理的一些高级技术，包括常见文件类型的处理、随机读写文件、内存映射文件、标准序列化机制，以及Jackson序列化。\n- 第五部分（第15～20章）介绍并发，包括线程的传统基础知识和Java并发包。传统基础知识包括线程的基本概念与基本的同步、协作和中断机制；Java并发包涵盖原子变量、显式锁、显式条件、并发容器、异步任务执行服务、同步和协作工具类。\n- 第六部分（第21～26章）介绍动态和声明式编程编程思路、API与技巧，涵盖反射、注解、动态代理、类加载机制、正则表达式、Java 8引入的函数式编程等。\n### 3. 本书特色\n&emsp;&emsp;简单来说，其他书大多教你怎么用，而这本书帮助你透彻理解，从基本概念到高层框架，剖析实现原理与JDK源代码，融合专业理论与应用实践，使你透彻理解Java编程的实现原理和思维逻辑，融会贯通。具体来说：\n1. 对于每个编程概念，不仅介绍了语法和用法，还分析了为什么要有这个概念，实现原理是什么，背后的思维逻辑是什么；\n2. 对于Java的主要API（如Java基础类、各种容器类、文件、并发包等），不仅介绍了用法、示例和应用，还剖析了大量JDK源代码，解释了其内部实现机制；\n3. 对于实践中常用的系统程序和框架，如键值数据库、消息队列、序列化框架、DI(依赖注入)容器、AOP(面向切面编程)框架、热部署、模板引擎等，本书利用基本API演示了其基本实现原理；\n4. 本书不仅注重实现原理，同样重视实用性，介绍了很多实践中常用的技术，包含了不少实际开发中积累的经验和教训，使读者可以少走一些弯路；\n5. 本书虽然是Java语言描述，但以更为通用的编程逻辑为主，融入了很多通用的编程相关知识，如二进制、编码、数据结构和算法、设计模式、操作系统、编程思维等；\n6. 本书高度注重表述，尽力站在读者的角度，循序渐进、简洁透彻、通俗易懂。\n### 4. 下载\n[PDF版本下载](https://pan.baidu.com/s/1spP2kUqJfmoBVATQ6HqwDg?pwd=igx8)'),
       (2, 2, '### 1. 作者简介\n&emsp;&emsp;刘汝佳，1982年12月生，高中毕业于重庆市外国语学校。2000年3月获得NOI2000全国青少年信息学奥林匹克竞赛一等奖第四名，进入国家集训队，并因此保送到清华大学计算机科学与技术系。大一时获2001年ACM/ICPC国际大学生程序设计竞赛亚洲-上海赛区冠军和2002年世界总决赛银牌（世界第四），2005年获学士学位，2008年获硕士学位。\n&emsp;&emsp;学生时代曾为中国计算机学会NOI科学委员会学生委员，担任IOI2002-2008中国国家队教练，并为NOI系列比赛命题十余道。现为NOI竞赛委员会委员，并在NOI 25周年时获得中国计算机学会颁发的“特别贡献奖”。\n&emsp;&emsp;2004年至今共为ACM/ICPC亚洲赛区命题二十余道，担任6次裁判和2次命题总监，并应邀参加IOI和ACM/ICPC相关国际研讨会，发表论文两篇。\n&emsp;&emsp;2004年初作为第一作者出版专著《算法艺术与信息学竞赛》，2009年出版译著《编程挑战》，2009年出版《算法竞赛入门经典》，2012年出版《算法竞赛入门经典——训练指南》。\n&emsp;&emsp;多年来在全国二十余个城市进行中学生竞赛培训工作，为北京、上海、吉隆坡等地的著名高校授课与宣讲，并多次与TopCoder、百度和网易有道等知名企业合作举办比赛，让更多的IT人才获得展示自我的平台。\n\n### 2. 内容简介\n<center>\n  <img src=\"/configImages/algorithm.jpg\" width=\"300\" height=\"410\"><img src=\"/configImages/algorithm2.jpg\" width=\"300\" height=\"410\">\n</center>\n\n&emsp;&emsp;《算法竞赛入门经典（第2版）》是一本算法竞赛的入门与提高教材，把C/C++语言、算法和解题有机地结合在一起，淡化理论，注重学习方法和实践技巧。全书内容分为12 章，包括程序设计入门、循环结构程序设计、数组和字符串、函数和递归、C++与STL入门、数据结构基础、暴力求解法、高效算法设计、动态规划初步、数学概念与方法、图论模型与算法、高级专题等内容，覆盖了算法竞赛入门和提高所需的主要知识点，并含有大量例题和习题。书中的代码规范、简洁、易懂，不仅能帮助读者理解算法原理，还能教会读者很多实用的编程技巧；书中包含的各种开发、测试和调试技巧也是传统的语言、算法类书籍中难以见到的。\n&emsp;&emsp;《算法竞赛入门经典（第2版）》可作为全国青少年信息学奥林匹克联赛（NOIP）复赛教材、全国青少年信息学奥林匹克竞赛（NOI）和ACM国际大学生程序设计竞赛（ACM/ICPC）的训练资料，也可作为IT工程师与科研人员的参考用书。\n\n### 3. 本书优势\n1. **系统性**：这些书籍通常会从基础概念开始，逐步引导读者深入到更复杂的算法和数据结构。\n2. **实用性**：它们通常包含大量的实际编程问题和解决方案，帮助读者理解算法在实际应用中的作用。\n3. **可读性**：好的入门书籍会用清晰、简洁的语言来解释复杂的概念，使得初学者也能够理解。\n4. **示例丰富**：通过大量的代码示例和练习题，读者可以更好地理解和掌握算法。\n5. **逐步引导**：书籍会按照难度递增的顺序组织内容，帮助读者逐步建立信心和技能。\n6. **理论联系实际**：除了理论讲解，这些书籍还会强调算法在实际编程竞赛中的应用，帮助读者将理论知识转化为实战能力。\n7. **社区支持**：一些经典书籍通常有活跃的读者社区，读者可以在社区中讨论问题、分享经验，获得额外的学习资源。\n8. **更新及时**：随着算法竞赛的发展，一些书籍会定期更新，以包含最新的算法和编程技巧。\n9. **作者权威**：这些书籍通常由在算法竞赛领域有丰富经验的专家编写，保证了内容的权威性和准确性。\n10. **适合自学**：即使是没有老师指导的情况下，读者也可以通过这些书籍自学，逐步提高自己的算法设计和编程能力。\n### 4. 下载\n[PDF版本下载](https://pan.baidu.com/s/1eN2tkY12PtsmfkuclDZ1dQ?pwd=fpw2)');


-- 初始化文章标签表
INSERT INTO `article_tag` (`id`, `article_id`, `tag_id`)
VALUES (8, 2, 100),
       (11, 1, 100);


-- 初始化配置表
INSERT INTO `config` (`id`, `type`, `name`, `banner_url`, `jump_url`, `content`, `status`)
VALUES (1, 2, '算法竞赛入门经典', '/configImages/algorithm.jpg', '/article/2', '清晰的讲解、丰富的实例帮助您快速提高算法能力！', 1),
       (2, 2, 'Java编程的逻辑', '/configImages/JavaLogic.png', '/article/1', '一本深入浅出、系统全面的Java编程指南。', 1);


-- 初始化用户表
INSERT INTO `user` (`id`, `username`, `password`, `salt_pos`)
VALUES (1, 'admin', '1adaa294aa82641a40636596cd7ece10', 3),
       (2, 'guest', 'b371152381d83b10031d6f52aa7d121b', 4);


-- 初始化用户信息表
INSERT INTO `user_info` (`id`, `user_id`, `nick_name`, `photo`, `position`, `company`, `profile`)
VALUES (1, 1, '管理员', '/upload/headers/add0da3c4d5d4653b9e28385aea1d872.jpg', '硕士研究生', '江苏大学计算机学院', '书山有路勤为径，学海无涯苦作舟。'),
       (2, 2, '帅气的游客', '/headers/0011.png', '', '', '');
