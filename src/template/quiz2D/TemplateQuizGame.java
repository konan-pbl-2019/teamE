package template.quiz2D;

import java.awt.Color;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.audio.BGM3D;
import framework.audio.Sound3D;
import framework.gameMain.SimpleScenarioGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
import framework.view3D.Camera3D;

public class TemplateQuizGame extends SimpleScenarioGame {
	private RWTFrame3D frame;
	private Sound3D fightBGM = BGM3D.registerBGM("data\\sound\\fightbgm.wav");
	
	@Override
	public void init(Universe universe, Camera3D camera) {		
		// シナリオの設定
		setScenario("data\\TemplateQuiz\\scenario.xml");
		container.setScenario(scenario);
		scenario.fire("開始");
		//BGM3D.playBGM(fightBGM);
	}

	@Override
	public RWTFrame3D createFrame3D() {
		frame = new RWTFrame3D();
		frame.setSize(1000, 800);
		frame.setTitle("Template for 2D Quiz Game");
		frame.setBackground(Color.BLACK);
		return frame;
	}
	


	@Override
	protected RWTContainer createRWTContainer() {
		container = new QuizGameContainer();
		return container;
	}
	
	@Override
	public void progress(RWTVirtualController virtualController, long interval) {
	}
	
	@Override
	public void showOption(int n, String option) {
		((QuizGameContainer)container).showOption(n, option);
	}

	@Override
	public void action(String action, Event event, ScenarioState nextState) {
		
		if (action.equals("openDialog")) {
			
		}else if(action.equals("right1")){
			BGM3D.playBGM(fightBGM);
			QuizGameContainer a = new QuizGameContainer();
			a.actionuke(1);
				
		}
		
		// シナリオ進行による世界への作用をここに書く
		if (action.equals("right")) {
		} else if (action.equals("wrong")) {
		}
	}

	/**
	 * ゲームのメイン
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		TemplateQuizGame game = new TemplateQuizGame();
		game.setFramePolicy(5, 33, false);
		game.start();
	}



}
