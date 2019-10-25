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
	private Sound3D startBGM =BGM3D.registerBGM("data\\sound\\sentou2.wav");
	private Sound3D endBGM =BGM3D.registerBGM("data\\sound\\gameover3.wav");
	private Sound3D clearBGM =BGM3D.registerBGM("data\\sound\\clear.wav");

	int life = 3;

	@Override
	public void init(Universe universe, Camera3D camera) {

		setScenario("data\\TemplateQuiz\\scenario.xml");
		container.setScenario(scenario);
		scenario.fire("äJén");

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
			BGM3D.playBGM(startBGM);
			((QuizGameContainer)container).haikei1();
		}



		if (action.equals("right10")) {
			((QuizGameContainer)container).seikai();


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
			scenario.go("èIóπ");
			BGM3D.playBGM(endBGM);
			life=3;
		}

		if(action.equals("fin")) {
			System.exit(0);
		}
		if(action.equals("wrong")) {
			((QuizGameContainer)container).batsu();

		}
		if(action.equals("righta")) {
			BGM3D.playBGM(clearBGM);
		}

		if(action.equals("right2a")) {
			BGM3D.playBGM(startBGM);
			((QuizGameContainer)container).haikei2();
			((QuizGameContainer)container).wave2enemy();
		}
		if(action.equals("right3a")) {
			BGM3D.playBGM(startBGM);
			((QuizGameContainer)container).haikei3();
			((QuizGameContainer)container).wave3enemy();
		}
	}


	public static void main(String[] args) {
		TemplateQuizGame game = new TemplateQuizGame();
		game.setFramePolicy(5, 33, false);
		game.start();
	}



}
