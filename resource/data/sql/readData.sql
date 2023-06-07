insert into Action select * from CSVREAD('./resource/data/csv/ACTION.csv',null,'charset=UTF-8');
insert into ACTIONANIMATION select * from CSVREAD('./resource/data/csv/ACTIONANIMATION.csv',null,'charset=UTF-8');
insert into ACTIONEVENT select * from CSVREAD('./resource/data/csv/ACTIONEVENT.csv',null,'charset=UTF-8');
insert into ACTIONTERM select * from CSVREAD('./resource/data/csv/ACTIONTERM.csv',null,'charset=UTF-8');
insert into ACTION_ACTIONEVENT select * from CSVREAD('./resource/data/csv/ACTION_ACTIONEVENT.csv',null,'charset=UTF-8');
insert into ACTION_ACTIONTERM select * from CSVREAD('./resource/data/csv/ACTION_ACTIONTERM.csv',null,'charset=UTF-8');
insert into BOOK select * from CSVREAD('./resource/data/csv/BOOK.csv',null,'charset=UTF-8');
insert into BOOK_ACTION select * from CSVREAD('./resource/data/csv/BOOK_ACTION.csv',null,'charset=UTF-8');
insert into CONDITION select * from CSVREAD('./resource/data/csv/CONDITION.csv',null,'charset=UTF-8');
insert into CONDITIONEFFECT select * from CSVREAD('./resource/data/csv/CONDITIONEFFECT.csv',null,'charset=UTF-8');
insert into CONDITION_CONDITIONEFFECT select * from CSVREAD('./resource/data/csv/CONDITION_CONDITIONEFFECT.csv',null,'charset=UTF-8');
insert into EQIPATTR select * from CSVREAD('./resource/data/csv/EQIPATTR.csv',null,'charset=UTF-8');
insert into EQIPSLOT select * from CSVREAD('./resource/data/csv/EQIPSLOT.csv',null,'charset=UTF-8');
insert into EQIPSTATUS select * from CSVREAD('./resource/data/csv/EQIPSTATUS.csv',null,'charset=UTF-8');
insert into EQIPTERM select * from CSVREAD('./resource/data/csv/EQIPTERM.csv',null,'charset=UTF-8');
insert into ITEM select * from CSVREAD('./resource/data/csv/ITEM.csv',null,'charset=UTF-8');
insert into ITEMUPGRADEEFFECT select * from CSVREAD('./resource/data/csv/ITEMUPGRADEEFFECT.csv',null,'charset=UTF-8');
insert into ITEMUPGRADEMATERIAL select * from CSVREAD('./resource/data/csv/ITEMUPGRADEMATERIAL.csv',null,'charset=UTF-8');
insert into ITEMUPGRADEVALUE select * from CSVREAD('./resource/data/csv/ITEMUPGRADEVALUE.csv',null,'charset=UTF-8');
insert into ITEM_ACTIONEVENT select * from CSVREAD('./resource/data/csv/ITEM_ACTIONEVENT.csv',null,'charset=UTF-8');
insert into ITEM_MATERIAL select * from CSVREAD('./resource/data/csv/ITEM_MATERIAL.csv',null,'charset=UTF-8');
insert into MATERIAL select * from CSVREAD('./resource/data/csv/MATERIAL.csv',null,'charset=UTF-8');
insert into QUEST select * from CSVREAD('./resource/data/csv/QUEST.csv',null,'charset=UTF-8');
insert into QUESTNPCADD select * from CSVREAD('./resource/data/csv/QUESTNPCADD.csv',null,'charset=UTF-8');
insert into QUESTNPCREMOVE select * from CSVREAD('./resource/data/csv/QUESTNPCREMOVE.csv',null,'charset=UTF-8');
insert into RACE select * from CSVREAD('./resource/data/csv/RACE.csv',null,'charset=UTF-8');
insert into RACE_SLOT select * from CSVREAD('./resource/data/csv/RACE_SLOT.csv',null,'charset=UTF-8');
insert into WEAPONTYPE select * from CSVREAD('./resource/data/csv/WEAPONTYPE.csv',null,'charset=UTF-8');



