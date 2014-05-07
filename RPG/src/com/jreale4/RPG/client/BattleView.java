package com.jreale4.RPG.client;

import java.util.Random;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.DoubleClickEvent;
import com.google.gwt.event.dom.client.DoubleClickHandler;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.Image;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.TextBox;
import com.google.gwt.user.client.ui.ValueBoxBase.TextAlignment;
import com.jreale4.RPG.shared.Enemy;
import com.jreale4.RPG.shared.Hero;
import com.google.gwt.user.client.ui.Label;

public class BattleView extends Composite {
	TextBox textBox;
	Integer totalHealthEnemy = 400;
	Integer totalHealthHero = 400;
	Enemy m = new Enemy(totalHealthEnemy);

	public BattleView(final Hero hero) {

		final LayoutPanel layoutPanel = new LayoutPanel();
		layoutPanel.setStyleName("layoutP");
		initWidget(layoutPanel);
		layoutPanel.setSize("1060px", "800px");

		textBox = new TextBox();
		textBox.setAlignment(TextAlignment.CENTER);
		textBox.setVisibleLength(80);
		textBox.setReadOnly(true);
		textBox.setVisible(false);

		// Health Bar code, BG is static background
		HTML healthDivBG = new HTML(
				"<div class=\"health-bar\">" +
				"<div class=\"health-bar-solid\"></div></div>");
		layoutPanel.add(healthDivBG);
		layoutPanel.setWidgetLeftWidth(healthDivBG, 197.0, Unit.PX, 400.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(healthDivBG, 78.0, Unit.PX, 34.0, Unit.PX);
		healthDivBG.setSize("400px", "34px");
		// Player copy

		// Health is red, under 50% total health
		final HTML healthDivLow = new HTML(
				"<div class=\"health-bar\">" +
				"<div class=\"health-bar-half-enemy\"></div></div>");
		layoutPanel.add(healthDivLow);
		layoutPanel.setWidgetLeftWidth(healthDivLow, 197.0, Unit.PX, 400.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(healthDivLow, 78.0, Unit.PX, 34.0, Unit.PX);
		healthDivLow.setSize("400px", "34px");
		healthDivLow.setVisible(false);

		// Health is green, over 50% total health
		final HTML healthDiv = new HTML(
				"<div class=\"health-bar\">" +
				"<div class=\"health-bar-normal-enemy\"></div></div>");
		layoutPanel.add(healthDiv);
		layoutPanel.setWidgetLeftWidth(healthDiv, 197.0, Unit.PX, 400.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(healthDiv, 78.0, Unit.PX, 34.0, Unit.PX);
		healthDiv.setSize("400px", "34px");

		// Special text box for battle announcements
		layoutPanel.add(textBox);
		textBox.setSize("400px", "34px");
		layoutPanel.setWidgetRightWidth(textBox, 463.0, Unit.PX, 400.0, Unit.PX);
		layoutPanel.setWidgetBottomHeight(textBox, 207.0, Unit.PX, 34.0, Unit.PX);

		// Label above enemy health bar
		final Label lblEnemyHealth = new Label("Enemy Health");
		lblEnemyHealth.setStyleName("high-health");
		layoutPanel.add(lblEnemyHealth);
		layoutPanel.setWidgetLeftWidth(lblEnemyHealth, 351.0, Unit.PX, 95.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblEnemyHealth, 56.0, Unit.PX, 16.0, Unit.PX);

		// Label above player health bar
		Label lblPlayerHealth = new Label("Player Health");
		lblPlayerHealth.setStyleName("high-health");
		layoutPanel.add(lblPlayerHealth);
		layoutPanel.setWidgetLeftWidth(lblPlayerHealth, 351.0, Unit.PX, 95.0, Unit.PX);
		layoutPanel.setWidgetTopHeight(lblPlayerHealth, 507.0, Unit.PX, 16.0, Unit.PX);

		Button btnAttack = new Button("Attack!");
		btnAttack.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {

				AttackRPC.attackService.makeSlash(hero, new AsyncCallback<Integer>(){
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub
					}

					public void onSuccess(Integer result) {
						textBox.setText("Dan used Slash! It did " + result + " damage.");
						textBox.setVisible(true);

						// Update Enemy health
						totalHealthEnemy -= (result * 10);
						if (totalHealthEnemy > 200) {
							layoutPanel.setWidgetLeftWidth(healthDiv, 197.0, Unit.PX, totalHealthEnemy, Unit.PX);
							layoutPanel.setWidgetTopHeight(healthDiv, 78.0, Unit.PX, 34.0, Unit.PX);							
						} else {
							lblEnemyHealth.setStyleName("half-health");
							healthDiv.setVisible(false);
							healthDivLow.setVisible(true);
							layoutPanel.setWidgetLeftWidth(healthDivLow, 197.0, Unit.PX, totalHealthEnemy, Unit.PX);
							layoutPanel.setWidgetTopHeight(healthDivLow, 78.0, Unit.PX, 34.0, Unit.PX);
						}

						EnemyTurn();

						if (totalHealthEnemy <= 0) {
							RPG.setView(new MapView(hero));
						}
					}
				});
			}
		});

		layoutPanel.add(btnAttack);
		layoutPanel.setWidgetLeftRight(btnAttack, 299.0, Unit.PX, 680.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(btnAttack, 399.0, Unit.PX, 373.0, Unit.PX);

		Button btnInventory = new Button("Inventory");
		btnInventory.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Open Inventory View, SAME VIEW accessed via MapView
				//RPG.setView(InView);
			}
		});

		layoutPanel.add(btnInventory);
		layoutPanel.setWidgetLeftRight(btnInventory, 409.0, Unit.PX, 570.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(btnInventory, 399.0, Unit.PX, 373.0, Unit.PX);

		Button btnEscape = new Button("Escape!");
		btnEscape.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				Random rand = new Random();
				int cnt = rand.nextInt(10)-2;
				if(cnt > 5){
					RPG.setView(new MapView(hero));
				}
				else{
					textBox.setText("Can't Escape!");
					textBox.setVisible(true);

					EnemyTurn();
				}
			}
		});

		layoutPanel.add(btnEscape);
		layoutPanel.setWidgetLeftRight(btnEscape, 409.0, Unit.PX, 570.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(btnEscape, 449.0, Unit.PX, 323.0, Unit.PX);

		Button btnMagica = new Button("Magica");
		btnMagica.addDoubleClickHandler(new DoubleClickHandler() {
			public void onDoubleClick(DoubleClickEvent event) {
				// Use last used magic attack, no windows/views
				// Use controller to retrieve and use last used magic
				AttackRPC.attackService.makeFire(hero, new AsyncCallback<Integer>(){

					@Override
					public void onFailure(Throwable caught) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onSuccess(Integer result) {
						textBox.setText("Dan used Fira! It did " + result + " damage.");
						textBox.setVisible(true);
						// Update Enemy health
						totalHealthEnemy -= (result * 10);
						if (totalHealthEnemy > 200) {
							layoutPanel.setWidgetLeftWidth(healthDiv, 197.0, Unit.PX, totalHealthEnemy, Unit.PX);
							layoutPanel.setWidgetTopHeight(healthDiv, 78.0, Unit.PX, 34.0, Unit.PX);							
						} else {
							lblEnemyHealth.setStyleName("half-health");
							healthDiv.setVisible(false);
							healthDivLow.setVisible(true);
							layoutPanel.setWidgetLeftWidth(healthDivLow, 197.0, Unit.PX, totalHealthEnemy, Unit.PX);
							layoutPanel.setWidgetTopHeight(healthDivLow, 78.0, Unit.PX, 34.0, Unit.PX);
						}

						EnemyTurn();

						if (totalHealthEnemy <= 0) {
							RPG.setView(new MapView(hero));
						}
					}
				});
			}
		});

		btnMagica.addClickHandler(new ClickHandler() {
			public void onClick(ClickEvent event) {
				// Open selection of available magic, current view
				// Magic has very small chance of MISSING the target, for now...
			}
		});

		layoutPanel.add(btnMagica);
		layoutPanel.setWidgetLeftRight(btnMagica, 299.0, Unit.PX, 680.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(btnMagica, 449.0, Unit.PX, 323.0, Unit.PX);

		Image image = new Image("assets/enemy_x.png");
		layoutPanel.add(image);
		layoutPanel.setWidgetLeftRight(image, 197.0, Unit.PX, 463.0, Unit.PX);
		layoutPanel.setWidgetTopBottom(image, 175.0, Unit.PX, 504.0, Unit.PX);
	}

	public void EnemyTurn(){
		AttackRPC.attackService.EnemyAttack(new AsyncCallback<Integer>(){

			@Override
			public void onFailure(Throwable caught) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onSuccess(Integer result) {

			}
		});
	}
}
