
create table PlayerChara(
StatusID varchar(8),
name varchar(16),
RaceID varchar(8),
location varchar(4),
StatusKey varchar(16),
max float,
min float,
val float,
od int,
exist boolean,
moveStopConditionID varchar(8)
);

create table PCStatus(
StatusID varchar(8),
StatusKey varchar(16),
max float,
min float,
val float
);

create table Race_Slot(
RaceID varchar(8),
SlotID varchar(8)
);

create table Race(
RaceID varchar(8),
itemBagSize int,
BookBagSize int
);

create table Status_Item(
StatusID varchar(8),
ItemID varchar(8),
Eqip boolean
);


create table Status_Condition(
StatusID varchar(8),
conditionID varchar(8)
);

create table Status_Book(
StatusID varchar(8),
BookID varchar(8)
);

create table PCAttr(
StatusID varchar(8),
AttrKey varchar(16),
max float,
min float,
val float
);

create table Status_Action(
StatusID varchar(8),
ActionID varchar(8)
);

create table Money(
Name varchar(32),
val int
);

create table CurrentFlag(
name varchar(64),
status varchar(3)
);

create table Status_ConditionEffect(
statusID varchar(8),
conditionEffectID varchar(8)
);

create table CurrentLocation(
MapID varchar(8),
x int,
y int
);

create table EqipSlot(
SlotID varchar(8),
visibleName varchar(8)
);

create table WeaponType(
WeaponTypeID varchar(8),
visibleName varchar(16),
desc varchar(256)
);

create table eqipStatus(
ItemID varchar(8),
targetName varchar(16),
val int
);

create table eqipAttr(
ItemID varchar(8),
targetName varchar(16),
val float
);

create table EqipTerm(
ItemID varchar(8),
type varchar(16),
targetName varchar(16),
val float
);

create table Action_ActionTerm(
ActionID varchar(8),
ActionTermID varchar(8)
);

create table ActionTerm(
ActionTermID varchar(8),
visibleName varchar(64),
termType varchar(32),
val varchar(16)
);

create table Quest(
QuestID varchar(8),
stage int,
title varchar(32),
desc varchar(256)
);

create table CurrentQuest(
QuestID varchar(8),
stage int
);

create table Item(
ItemID varchar(8),
visibleName varchar(64),
desc varchar(256),
val int,
slotID varchar(8),
WeaponType varchar(8),
area int,
canSale boolean,
currentUpgradeNum int,
dcsCSV varchar(24),
waitTime int,
SoundID varchar(8),
attackCount int,
selectType varchar(8),
IFF boolean,
defaultTargetTeam varchar(8),
switchTeam boolean,
Targeting boolean
);

create table Action(
ActionID varchar(8),
type varchar(16),
visibleName varchar(64),
desc varchar(256),
area int,
waitTime int,
SoundID varchar(8),
attackCount int,
sortNo int,
spellTime int,
dcsCSV varchar(24),
selectType varchar(8),
IFF boolean,
defaultTargetTeam varchar(8),
switchTeam boolean,
Targeting boolean
);

create table QuestNPCRemove(
QuestID varchar(8),
stage int,
mapID varchar(8),
NPCID varchar(8)
);

create table QuestNPCAdd(
QuestID varchar(8),
stage int,
mapID varchar(8),
NPCID varchar(8)
);

create table Item_Material(
ItemID varchar(8),
MaterialID varchar(8)
);

create table Item_ActionEvent(
ItemID varchar(8),
ItemEventID varchar(8)
);

create table LastSave(
tim bigint
);

create table MaterialBag(
MaterialID varchar(8),
num int
);

create table PageBag(
PageID varchar(8),
num int
);

create table Action_ActionEvent(
ActionID varchar(8),
ActionEventID varchar(8)
);

create table Condition(
conditionID varchar(8),
desc varchar(32),
priority int
);

create table Material(
MaterialID varchar(8),
visibleName varchar(32),
val int
);

create table ActionEvent(
ActionEventID varchar(8),
battle boolean,
field boolean,
desc varchar(256),
targetType varchar(16),
parameterType varchar(16),
targetName varchar(16),
attr varchar(16),
val int,
p float,
spread float,
dct varchar(32),
animationID varchar(8),
animationMoveType varchar(32)
);

create table itemUpgradeValue(
ItemID varchar(8),
od int,
val int
);

create table ItemUpgradeEffect(
ItemID varchar(8),
od int,
tgtNameCSV varchar(48),
valCSV varchar(48)
);

create table ITEMUPGRADEMaterial(
ItemID varchar(8),
od int,
MaterialID varchar(8),
num int
);


create table ActionAnimation(
animationID varchar(8),
fileName varchar(128),
w int,
h int,
tc varchar(32),
mg float
);

create table Condition_ConditionEffect(
conditionID varchar(8),
ConditionEffectID varchar(8)
);

create table Page(
PageID varchar(8),
MagicCompositeType varchar(32),
visibleName varchar(32),
targetName varchar(16),
val float
);

create table Book_Page(
BookID varchar(8),
PageID varchar(8)
);

create table Book(
BookID varchar(8),
visibleName varchar(32),
desc varchar(256),
val int
);

create table Book_Action(
BookID varchar(8),
ActionID varchar(8)
);

create table ConditionEffect(
ConditionEffectID varchar(8),
EffectContinueType varchar(16),
time int,
EffectTargetType varchar(16),
EffectSetType varchar(16),
targetName varchar(16),
val float,
p float,
desc varchar(128)
);

