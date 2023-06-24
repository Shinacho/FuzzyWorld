/*
 * Copyright (C) 2023 Shinacho
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package fuzzyworld;

import java.util.ArrayList;
import java.util.List;
import kinugasa.game.system.ActionEventResult;
import kinugasa.game.system.ActionEventStorage;
import kinugasa.game.system.ActionResultType;
import kinugasa.game.system.ActionTarget;
import kinugasa.game.system.BattleCharacter;
import kinugasa.game.system.ConditionStorage;
import kinugasa.game.system.CustomActionEvent;
import kinugasa.graphics.Animation;
import kinugasa.object.AnimationSprite;
import kinugasa.util.Random;

/**
 *
 * @vesion 1.0.0 - May 21, 2023_9:04:32 PM<br>
 * @author Shinacho<br>
 */
public class UserDefineActionBuilder {

	static void addActionEvent() {
		//---------------------------------------------------------------------
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08991") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(1f)) {
					ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08992") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.75f)) {
					ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08993") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.5f)) {
					ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08994") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.25f)) {
					ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08995") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.125f)) {
					ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08996") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(1f)) {
					bc.getStatus().getItemBag().drop(tgt.getAction().getName());
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createEmptySprite());
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08997") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.75f)) {
					bc.getStatus().getItemBag().drop(tgt.getAction().getName());
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createEmptySprite());
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08998") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.5f)) {
					bc.getStatus().getItemBag().drop(tgt.getAction().getName());
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createEmptySprite());
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E08999") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.25f)) {
					bc.getStatus().getItemBag().drop(tgt.getAction().getName());
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createEmptySprite());
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E09000") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getUser();
				if (Random.percent(0.125f)) {
					bc.getStatus().getItemBag().drop(tgt.getAction().getName());
				}
				return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createEmptySprite());
			}
		});
		//---------------------------------------------------------------------

		//---------------------------------------------------------------------
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18030") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(1f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}

				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18031") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.75f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18032") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.5f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18033") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.25f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18034") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.125f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18035") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(1f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18036") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.75f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18037") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.5f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18038") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.25f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E18039") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.125f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		//---------------------------------------------------------------------

		//---------------------------------------------------------------------
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27030") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(1f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}

				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27031") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.75f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27032") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.5f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27033") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.25f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27034") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.125f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27035") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(1f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27036") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.75f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27037") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.5f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27038") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.25f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E27039") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.125f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		//---------------------------------------------------------------------
		//---------------------------------------------------------------------
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36030") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(1f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}

				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36031") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.75f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36032") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.5f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36033") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.25f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36034") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.125f)) {
						ConditionStorage.getInstance().getAll().forEach(p -> bc.getStatus().removeCondition(p.getName()));
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createAnimationSprite(bc, "AA02"));
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36035") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(1f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36036") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.75f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36037") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.5f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36038") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.25f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		ActionEventStorage.getInstance().add(new CustomActionEvent("E36039") {
			{
				setBattle(true);
				setField(true);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				List<ActionResultType> t = new ArrayList<>();
				List<AnimationSprite> sp = new ArrayList<>();
				for (BattleCharacter bc : tgt) {
					if (Random.percent(0.125f)) {
						bc.getStatus().getItemBag().drop(tgt.getAction().getName());
						t.add(ActionResultType.SUCCESS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					} else {
						t.add(ActionResultType.MISS);
						sp.add(UserDefineActionBuilder.createEmptySprite());
					}
				}
				return new ActionEventResult(t, sp);
			}
		});
		//---------------------------------------------------------------------

		ActionEventStorage.getInstance().add(new CustomActionEvent("A0027_1") {
			{
				setBattle(true);
				setField(false);
			}

			@Override
			public ActionEventResult exec(ActionTarget tgt) {
				BattleCharacter bc = tgt.getTarget().get(0);
				if (Random.percent(0.9f)) {
					if (bc.getStatus().hasCondition("C_GOLDY")) {
						bc.getStatus().getBaseStatus().get("HP").set(0);
						bc.getStatus().addCondition("C_DEAD");
						return new ActionEventResult(ActionResultType.SUCCESS, UserDefineActionBuilder.createAnimationSprite(bc, "AA01"));
					}
				}
				return new ActionEventResult(ActionResultType.MISS, null);
			}
		});
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		

	}

	//アニメーションが個別で必要な場合はこのメソッドからIDでDBから取る。
	//AWT光線アニメーションは別のAEとしてAに入っているので、取る必要はない。
	private static AnimationSprite createAnimationSprite(BattleCharacter tgt, String id) {
		Animation a = ActionEventStorage.getInstance().getAnimationByID(id);
		a.setRepeat(false);
		AnimationSprite sp = new AnimationSprite(a);
		sp.setLocationByCenter(tgt.getCenter());
		return sp;
	}

	static AnimationSprite createEmptySprite() {
		return new AnimationSprite();
	}
}
