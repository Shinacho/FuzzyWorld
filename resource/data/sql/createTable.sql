create table S_counts(
name varchar(32) primary key,
num integer
);

create table S_PlayerChara(
StatusID varchar(8) primary key,
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

create table S_PCStatus(
StatusID varchar(8),
StatusKey varchar(16),
max float,
min float,
val float,
primary key(StatusID,StatusKey)
);

create table Race_Slot(
RaceID varchar(8),
SlotID varchar(8),
primary key(RaceID, SlotID)
);

create table Race(
RaceID varchar(8) primary key,
itemBagSize int,
BookBagSize int
);

create table S_Status_Item(
StatusID varchar(8),
ItemID varchar(8),
od integer,
Eqip boolean,
primary key(StatusID, ItemID, od)
);


create table S_Status_Condition(
StatusID varchar(8),
conditionID varchar(8),
primary key(StatusID, conditionID)
);

create table S_Status_Book(
StatusID varchar(8),
BookID varchar(8),
od integer,
primary key(StatusID, BookID, od)
);

create table S_PCAttr(
StatusID varchar(8),
AttrKey varchar(16),
max float,
min float,
val float,
primary key(StatusID, ATTRKEY)
);

create table S_Status_Action(
StatusID varchar(8),
ActionID varchar(8),
primary key(StatusID, ActionID)
);

create table S_Money(
Name varchar(32) primary key,
val int
);

create table S_CurrentFlag(
name varchar(64) primary key,
status varchar(3)
);

create table S_Status_ConditionEffect(
statusID varchar(8),
conditionEffectID varchar(8),
primary key(StatusID, ConditionEffectID)
);

create table S_CurrentLocation(
MapID varchar(8) primary key,
x int,
y int
);

create table EqipSlot(
SlotID varchar(8) primary key,
visibleName varchar(8)
);

create table WeaponType(
WeaponTypeID varchar(8) primary key,
visibleName varchar(16),
desc varchar(256)
);

create table eqipStatus(
ItemID varchar(8) primary key,
targetName varchar(16),
val int
);

create table eqipAttr(
ItemID varchar(8) primary key,
targetName varchar(16),
val float
);

create table EqipTerm(
ItemID varchar(8),
type varchar(16),
targetName varchar(16),
val float,
primary key(ItemID, type, targetName, val)
);

create table Action_ActionTerm(
ActionID varchar(8),
ActionTermID varchar(8),
primary key(ActionID,ActionTermID)
);

create table ActionTerm(
ActionTermID varchar(8) primary key,
termType varchar(64),
val varchar(64),
visibleName varchar(128)
);

create table Quest(
QuestID varchar(8) primary key,
type varchar(8),
stage int,
title varchar(32),
desc varchar(256)
);

create table S_CurrentQuest(
QuestID varchar(8) primary key,
stage int
);

create table Item(
ItemID varchar(8) primary key,
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
selfTarget boolean,
Targeting boolean
);

create table Action(
ActionID varchar(8) primary key,
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
selfTarget boolean,
Targeting boolean
);

create table QuestNPCRemove(
QuestID varchar(8),
stage int,
mapID varchar(8),
NPCID varchar(8),
primary key(QuestID, stage, mapID, NPCID)
);

create table QuestNPCAdd(
QuestID varchar(8),
stage int,
mapID varchar(8),
NPCID varchar(8),
primary key(QuestID, stage, mapID, NPCID)
);

create table Item_Material(
ItemID varchar(8),
MaterialID varchar(8),
primary key(ItemID, MaterialID)
);

create table Item_ActionEvent(
ItemID varchar(8),
ItemEventID varchar(8),
primary key(ItemID, ItemEventID)
);

create table S_LastSave(
tim bigint primary key
);

create table S_MaterialBag(
MaterialID varchar(8) primary key,
num int
);
create table S_PageBag(
ActionEventID varchar(8) primary key,
num int
);

create table Action_ActionEvent(
ActionID varchar(8),
ActionEventID varchar(8),
primary key(ActionID,ActionEventID)
);

create table Condition(
conditionID varchar(32) primary key,
desc varchar(32),
priority int
);

create table Material(
MaterialID varchar(8) primary key,
visibleName varchar(32),
val int
);

create table ActionEvent(
ActionEventID varchar(8) primary key,
battle boolean,
field boolean,
desc varchar(256),
targetType varchar(16),
parameterType varchar(16),
targetName varchar(16),
attr varchar(16),
val float,
p float,
spread float,
dct varchar(32),
animationID varchar(8),
animationMoveType varchar(32)
);

create table itemUpgradeValue(
ItemID varchar(8),
od int,
val int,
primary key(ItemID, od)
);

create table ItemUpgradeEffect(
ItemID varchar(8),
od int,
tgtNameCSV varchar(48),
valCSV varchar(48),
primary key(ItemID,od)
);

create table ITEMUPGRADEMaterial(
ItemID varchar(8),
od int,
MaterialID varchar(8),
num int,
primary key(ItemID, od)
);


create table ActionAnimation(
animationID varchar(8) primary key,
desc varchar(128),
fileName varchar(128),
w int,
h int,
tc varchar(32),
mg float
);

create table Condition_ConditionEffect(
conditionID varchar(24),
ConditionEffectID varchar(24),
primary key(ConditionID, conditionEffectID)
);

create table Book(
BookID varchar(8) primary key,
visibleName varchar(32),
desc varchar(256),
val int
);

create table Book_Action(
BookID varchar(8),
ActionID varchar(8),
primary key(BookID,ActionID)
);

create table ConditionEffect(
ConditionEffectID varchar(8) primary key,
EffectContinueType varchar(32),
tim int,
EffectTargetType varchar(32),
EffectSetType varchar(32),
targetName varchar(32),
val float,
p float,
desc varchar(128)
);

