
create table Sound(
SoundID varchar(8),
desc varchar(256),
FileName varchar(128),
mg float,
loopFrom int,
loopTo int,
type varchar(3)
);


truncate table Sound;


-------------------------------  SOUND
insert into sound values('SD0001','凄惨な場面で使うつもり作りました。','resource/bgm/おぞましい真実.wav',0.66,-1,0,'BGM');
insert into sound values('SD0003','ハッピーな感じなのにFWっぽい感じにしました。','resource/bgm/お祭りの町.wav',0.66,-1,0,'BGM');
insert into sound values('SD0004','','resource/bgm/さわやかな冒険.wav',0.66,-1,0,'BGM');
insert into sound values('SD0005','','resource/bgm/とてものどかな村.wav',0.66,-1,0,'BGM');
insert into sound values('SD0008','遥か昔ソナーで作った曲。/昔作った曲は譜面データはあるのですが、ソフトを変えたので読めません。/よって当時出しておいたwavしか聞く手段がないのです。/','resource/bgm/のどかな村.wav',0.66,-1,0,'BGM');
insert into sound values('SD0009','','resource/bgm/オープニング.wav',0.33,-1,0,'BGM');
insert into sound values('SD0010','FFっぽくしたかった曲。','resource/bgm/まあいいか.wav',0.66,-1,0,'BGM');
insert into sound values('SD0011','クロノトリガーの原始時代っぽくしたかった曲。','resource/bgm/エスニック・プレース.wav',0.66,-1,0,'BGM');
insert into sound values('SD0012','','resource/bgm/エンディング.wav',0.66,-1,0,'BGM');
insert into sound values('SD0013','遥か昔ソナーで作った曲。/多分FWに入ってる曲では一番古いんじゃないかな。/FWに使われている衣笠フレームワークは開発初期段階では/シューティングゲームをテスト用の題材にしていたのです。/もちろん今でもSTG用の処理が残っているので作ろうと思えば作れますよ。','resource/bgm/シューティング・ステージ１.wav',0.66,-1,0,'BGM');
insert into sound values('SD0014','遥か昔ソナーで作った曲。タムタムしていてガンヘッドみたいです。','resource/bgm/シューティング・ステージ２.wav',0.66,-1,0,'BGM');
insert into sound values('SD0016','3面っぽくないけど3面用に作った曲。','resource/bgm/シューティング・ステージ３.wav',0.66,-1,0,'BGM');
insert into sound values('SD0017','4面っぽくないけど4面用に作った曲。','resource/bgm/シューティング・ステージ４.wav',0.66,-1,0,'BGM');
insert into sound values('SD0018','','resource/bgm/デジタル空間.wav',0.66,-1,0,'BGM');
insert into sound values('SD0019','メインのバトル曲です。通常用。','resource/bgm/バトル1（リメイク）.wav',0.3,-1,0,'BGM');
insert into sound values('SD0020','メインのバトル曲です。通常用。','resource/bgm/バトル１（新）.wav',0.33,-1,0,'BGM');
insert into sound values('SD0021','遥か昔ソナーで作った曲。メインのバトル曲です。通常用。','resource/bgm/バトル１（旧）.wav',0.3,-1,0,'BGM');
insert into sound values('SD0022','メインのバトル曲です。対ベルマ用。','resource/bgm/バトル１～２.wav',0.3,-1,0,'BGM');
insert into sound values('SD0023','メインのバトル曲です。対ベルマ用。/昔作った（旧）もあるんですがなぜか再生できないので入ってません。','resource/bgm/バトル２（新）.wav',0.33,-1,0,'BGM');
insert into sound values('SD0024','クロノトリガーっぽくして/ああ大ボスだってわかるようにしたかった曲','resource/bgm/バトル３.wav',0.3,-1,0,'BGM');
insert into sound values('SD0025','遥か昔ソナーで作った曲。メインテーマ。/異世界を冒険してる感じです。/elonaのオープニングで流れても違和感ない感じを目指しました。','resource/bgm/フィールド.wav',0.44,-1,0,'BGM');
insert into sound values('SD0026','遥か昔ソナーで作った曲。/メインテーマ。','resource/bgm/フィールド２.wav',0.78,-1,0,'BGM');
insert into sound values('SD0027','遥か昔ソナーで作った曲。/メインテーマ。','resource/bgm/フィールド３.wav',0.66,-1,0,'BGM');
insert into sound values('SD0028','４は譜面データしか残ってないのでwavがありません。/メインテーマ。広大な世界を冒険してる感じです。','resource/bgm/フィールド５.wav',0.3,-1,1017696,'BGM');
insert into sound values('SD0029','','resource/bgm/ボーナスゲーム.wav',0.66,-1,0,'BGM');
insert into sound values('SD0030','遥か昔ソナーで作った曲。/三拍子で三連符を使いたかったやつ。','resource/bgm/ユニークな街.wav',0.66,-1,0,'BGM');
insert into sound values('SD0031','クロノトリガーの黒の夢っぽくしたかった曲','resource/bgm/ラストダンジョン.wav',0.3,-1,0,'BGM');
insert into sound values('SD0032','4拍子と5拍子を行ったり来たりする曲。/正直言うといいタイトルが思いつかなかった。','resource/bgm/不思議なダンジョン.wav',0.3,-1,0,'BGM');
insert into sound values('SD0033','森っぽい曲。思いついてから完成までに地味に結構時間かかりました。','resource/bgm/不思議な森.wav',0.66,-1,0,'BGM');
insert into sound values('SD0034','遥か昔ソナーで作った曲。','resource/bgm/不気味なダンジョン.wav',0.66,-1,0,'BGM');
insert into sound values('SD0035','白銀城で使えそうな曲。/この世界には何やら裏があり、/世界の真実を知ったときに使う予定で作りました。','resource/bgm/世界の真相.wav',0.66,-1,0,'BGM');
insert into sound values('SD0036','遥か昔ソナーで作った曲。/新卒の頃神戸で新入社員研修を受けながら夜作ったやつ。/そのために飲み会とか断ってたから変な奴だと思われていたでしょう。/','resource/bgm/中ボス.wav',0.66,-1,0,'BGM');
insert into sound values('SD0037','','resource/bgm/作戦会議.wav',0.66,-1,0,'BGM');
insert into sound values('SD0038','','resource/bgm/刻一刻.wav',0.66,-1,0,'BGM');
insert into sound values('SD0039','遥か昔ソナーで作った曲。','resource/bgm/厳かなダンジョン.wav',0.66,-1,0,'BGM');
insert into sound values('SD0040','遥か昔ソナーで作った曲。スカイリムっぽい奴。','resource/bgm/変な村.wav',0.66,-1,0,'BGM');
insert into sound values('SD0041','マントー、もしくは菊五郎','resource/bgm/変質者との戦い.wav',0.66,-1,0,'BGM');
insert into sound values('SD0042','マントー、もしくは菊五郎','resource/bgm/変質者のテーマ.wav',0.66,-1,0,'BGM');
insert into sound values('SD0043','','resource/bgm/夢の世界.wav',0.66,-1,0,'BGM');
insert into sound values('SD0044','ベースが響いていいでしょ。','resource/bgm/天空の魔法都市.wav',0.66,-1,0,'BGM');
insert into sound values('SD0045','','resource/bgm/奇襲攻撃！.wav',0.66,-1,0,'BGM');
insert into sound values('SD0046','','resource/bgm/妖精の森.wav',0.66,-1,0,'BGM');
insert into sound values('SD0047','','resource/bgm/小ボス.wav',0.66,-1,0,'BGM');
insert into sound values('SD0048','','resource/bgm/工業都市.wav',0.66,-1,0,'BGM');
insert into sound values('SD0049','遥か昔ソナーで作った曲。/7拍子のイケてるやつ。','resource/bgm/師匠とのイベント戦.wav',0.66,-1,0,'BGM');
insert into sound values('SD0050','','resource/bgm/廃れた都市.wav',0.66,-1,0,'BGM');
insert into sound values('SD0051','','resource/bgm/怪しい取引.wav',0.66,-1,0,'BGM');
insert into sound values('SD0052','','resource/bgm/悪の軍団.wav',0.66,-1,0,'BGM');
insert into sound values('SD0053','','resource/bgm/愉快または間抜け.wav',0.66,-1,0,'BGM');
insert into sound values('SD0054','','resource/bgm/探検.wav',0.66,-1,0,'BGM');
insert into sound values('SD0055','ベルマ国のテーマ。','resource/bgm/斜陽の帝国.wav',0.3,-1,0,'BGM');
insert into sound values('SD0056','遥か昔ソナーで作った曲。','resource/bgm/明るいダンジョン.wav',0.66,-1,0,'BGM');
insert into sound values('SD0057','遥か昔ソナーで作った曲。','resource/bgm/明るい街.wav',0.66,-1,0,'BGM');
insert into sound values('SD0058','遥か昔ソナーで作った曲。','resource/bgm/普通な感じの街.wav',0.66,-1,0,'BGM');
insert into sound values('SD0059','','resource/bgm/残酷な運命.wav',0.66,-1,0,'BGM');
insert into sound values('SD0060','ムロキのテーマ。気怠い感じです。やれやれ。','resource/bgm/気怠い探偵.wav',0.66,-1,0,'BGM');
insert into sound values('SD0061','シャンシャンシャン！雪っぽい！','resource/bgm/氷のダンジョン.wav',0.66,-1,0,'BGM');
insert into sound values('SD0062','','resource/bgm/決意.wav',0.66,-1,0,'BGM');
insert into sound values('SD0063','','resource/bgm/洞窟.wav',0.66,-1,0,'BGM');
insert into sound values('SD0064','','resource/bgm/灼熱の地獄.wav',0.66,-1,0,'BGM');
insert into sound values('SD0065','狂ってる人用BGM。','resource/bgm/狂気の人.wav',0.3,-1,0,'BGM');
insert into sound values('SD0066','ダレス国のテーマ。','resource/bgm/王都.wav',0.66,-1,0,'BGM');
insert into sound values('SD0067','機械獣デデべっぽくしたかった曲。/7拍子のイケてるやつ。','resource/bgm/異形のものとの戦い.wav',0.3,-1,0,'BGM');
insert into sound values('SD0068','かわいそうな女の子（ニーナ）のテーマ。','resource/bgm/疑い.wav',0.66,-1,0,'BGM');
insert into sound values('SD0069','遥か昔ソナーで作った曲。','resource/bgm/眠らない大都会.wav',0.66,-1,0,'BGM');
insert into sound values('SD0070','','resource/bgm/睨み合い.wav',0.66,-1,0,'BGM');
insert into sound values('SD0071','','resource/bgm/知的探求.wav',0.66,-1,0,'BGM');
insert into sound values('SD0072','','resource/bgm/神殿.wav',0.66,-1,0,'BGM');
insert into sound values('SD0073','','resource/bgm/結末.wav',0.66,-1,0,'BGM');
insert into sound values('SD0074','「変な村」のリメイク。','resource/bgm/見知らぬ土地・見知らぬ文化.wav',0.3,-1,0,'BGM');
insert into sound values('SD0075','大戦略エキスパートっぽくしたかった曲','resource/bgm/軍事作戦２.wav',0.66,-1,0,'BGM');
insert into sound values('SD0076','','resource/bgm/重厚なダンジョン.wav',0.66,-1,0,'BGM');
insert into sound values('SD0077','','resource/bgm/静かなる決意.wav',0.66,-1,0,'BGM');
insert into sound values('SD0078','天狗の庵みたいなBGM。','resource/bgm/静寂空間.wav',0.66,-1,0,'BGM');
insert into sound values('SD0079','エリーのテーマ。/少し寂しい感じですが一人で生きぬいてきたたくましさも感じます。','resource/bgm/風の魔法使いのテーマ.wav',0.66,-1,0,'BGM');
insert into sound values('SD0080','','resource/bgm/風の魔法使いの戦い.wav',0.66,-1,0,'BGM');
insert into sound values('SD0081','','resource/bgm/風の魔法使いの戦い２.wav',0.66,-1,0,'BGM');
insert into sound values('SD0082','','resource/bgm/魔力の間.wav',0.66,-1,0,'BGM');
insert into sound values('SD0083','','resource/bgm/苦難の時代.wav',0.66,-1,0,'BGM');
insert into sound values('SD0084','錬金術がもたらす不思議と繁栄を表そうとした一曲です。','resource/bgm/錬金術、輝きの時代.wav',0.66,-1,0,'BGM');




insert into sound values('SD1000','','resource/se/screenShot.wav',1,0,0,'SE');
insert into sound values('SD1002','','resource/se/SS.wav',1,0,0,'SE');
insert into sound values('SD1003','','resource/se/ゲームスタート.wav',1,0,0,'SE');
insert into sound values('SD1004','','resource/se/バトルターン開始.wav',1,0,0,'SE');
insert into sound values('SD1005','','resource/se/ブロック.wav',0.5,0,0,'SE');
insert into sound values('SD1006','','resource/se/避けた.wav',1,0,0,'SE');
insert into sound values('SD1007','','resource/se/魔法詠唱.wav',1.75,0,0,'SE');
insert into sound values('SD1008','','resource/se/選択1.wav',1,0,0,'SE');
insert into sound values('SD1009','','resource/se/選択2.wav',1,0,0,'SE');
insert into sound values('SD1010','','resource/se/足音.wav',1.75,0,0,'SE');
insert into sound values('SD1011','','resource/se/敵消滅.wav',1,0,0,'SE');
insert into sound values('SD1012','','resource/se/光線魔法.wav',1,0,0,'SE');
insert into sound values('SD1013','','resource/se/回復.wav',1,0,0,'SE');
insert into sound values('SD1014','','resource/se/回復魔法.wav',1,0,0,'SE');
insert into sound values('SD1015','','resource/se/地魔法.wav',1,0,0,'SE');
insert into sound values('SD1016','','resource/se/地魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1017','','resource/se/戦闘終了（勝利）.wav',1,0,0,'SE');
insert into sound values('SD1018','','resource/se/戦闘開始.wav',1,0,0,'SE');
insert into sound values('SD1019','','resource/se/斬撃.wav',1,0,0,'SE');
insert into sound values('SD1020','','resource/se/時空魔法.wav',1,0,0,'SE');
insert into sound values('SD1021','','resource/se/殴打.wav',1,0,0,'SE');
insert into sound values('SD1022','','resource/se/殴打２.wav',1,0,0,'SE');
insert into sound values('SD1023','','resource/se/水魔法.wav',1,0,0,'SE');
insert into sound values('SD1024','','resource/se/氷魔法.wav',1,0,0,'SE');
insert into sound values('SD1025','','resource/se/氷魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1026','','resource/se/海岸.wav',1,0,0,'SE');
insert into sound values('SD1027','','resource/se/炎魔法.wav',1,0,0,'SE');
insert into sound values('SD1028','','resource/se/炎魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1029','','resource/se/熱魔法.wav',1,0,0,'SE');
insert into sound values('SD1030','','resource/se/熱魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1031','','resource/se/破壊光線魔法（強）.wav',1,0,0,'SE');
insert into sound values('SD1032','','resource/se/神秘魔法.wav',1,0,0,'SE');
insert into sound values('SD1033','','resource/se/精神魔法.wav',1,0,0,'SE');
insert into sound values('SD1034','','resource/se/通常攻撃.wav',1,0,0,'SE');
insert into sound values('SD1035','','resource/se/錬金魔法.wav',1,0,0,'SE');
insert into sound values('SD1036','','resource/se/錬金魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1037','','resource/se/雷魔法.wav',1,0,0,'SE');
insert into sound values('SD1038','','resource/se/風魔法.wav',1,0,0,'SE');
insert into sound values('SD1039','','resource/se/風魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1040','','resource/se/魔法単体.wav',1,0,0,'SE');
insert into sound values('SD1041','','resource/se/雷魔法三連.wav',1,0,0,'SE');
insert into sound values('SD1042','','resource/se/ショック.wav',1,0,0,'SE');