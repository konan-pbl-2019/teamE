package template.quiz2D;

import java.awt.Color;

import framework.RWT.RWTContainer;
import framework.RWT.RWTFrame3D;
import framework.RWT.RWTVirtualController;
import framework.gameMain.SimpleScenarioGame;
import framework.model3D.Universe;
import framework.scenario.Event;
import framework.scenario.ScenarioState;
import framework.view3D.Camera3D;

public class TemplateQuizGame extends SimpleScenarioGame {
	private RWTFrame3D frame;
	private Sound3D startBGM =BGM3D.registerBGM("data\\sound\\sentou2.wav");
	private Sound3D endBGM =BGM3D.registerBGM("data\\sound\\gameover3.wav");
	private Sound3D clearBGM =BGM3D.registerBGM("data\\sound\\clear.wav");

	int life = 3;//残機

	@Override
	public void init(Universe universe, Camera3D camera) {
		// シナリオの設定
		setScenario("data\\TemplateQuiz\\scenario.xml");
		container.setScenario(scenario);
		scenario.fire("開始");

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
		// シナリオ進行による世界への作用をここに書く

		if (action.equals("openDialog")) {
			BGM3D.playBGM(startBGM);
			((QuizGameContainer)container).haikei();



		if (action.equals("right")) {

		} else if (action.equals("wrong")) {
			life--;

		} else if(action.equals("right1")) {
			((QuizGameContainer)container).haikei1();
			((QuizGameContainer)container).wave1enemy();
		} else if(action.equals("right2")) {
			((QuizGameContainer)container).haikei2();
			((QuizGameContainer)container).wave2enemy();
		} else if(action.equals("right3")) {
			((QuizGameContainer)container).haikei3();
			((QuizGameContainer)container).wave3enemy();
		}

		if(life==0) {
			scenario.go("終了");
			life=3;
		}

		if(action.equals("fin")) {
			System.exit(0);
		}
		if(action.equals("wrong")) {
			((QuizGameContainer)container).batsu();
			BGM3D.playBGM(endBGM);
			
		}
		if(action.equals("righta")) {
			BGM3D.playBGM(clearBGM);
		}
		
		if(action.equals("right2a")) {
			BGM3D.playBGM(startBGM);
			((QuizGameContainer)container).haikei();
			((QuizGameContainer)container).wave2enemy();
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
