package template.quiz2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.GraphicsConfiguration;

import framework.RWT.RWTButton;
import framework.RWT.RWTImage;
import framework.RWT.RWTVirtualController;
import framework.RWT.RWTVirtualKey;
import framework.RWT.RWTWidget;
import framework.audio.Sound3D;
import framework.gameMain.BaseScenarioGameContainer;

/**
 * �N�C�Y�Q�[���p���
 * @author Nitta
 *
 */
public class QuizGameContainer extends BaseScenarioGameContainer {

	private RWTButton[] optionButtons = new RWTButton[4];
	private Sound3D sentaku = new Sound3D("data\\sound\\cursor10.wav");
	private Sound3D kettei = new Sound3D("data\\sound\\decision13.wav");
	private int seikai;
	private RWTImage image;
	private RWTImage image2;

	public QuizGameContainer() {
		super();
	}

	@Override
	public void build(GraphicsConfiguration gc) {
		super.build(gc);
		canvas.setRelativePosition(0.0f, -0.3f);		// 3D�\�����̍���[
		canvas.setRelativeSize(1.0f, 1.0f);		// 3D�\�����̃T�C�Y
		addCanvas(canvas);

		image = new RWTImage("data\\images\\quizhaikei.jpg");
		image.setRelativePosition(0.0f, -0.2f);
		image.setSize(1000, 1000);
		canvas.addWidget(image);

		dialog.setRelativePosition(0.2f, 0.75f);	// �_�C�A���O
		dialog.setFont(new Font("", Font.PLAIN, 12));	// �����̃t�H���g
		dialog.setColor(Color.WHITE);				// �����̐F
		addWidget(dialog);

		Font f = new Font("", Font.PLAIN, 30);
		optionButtons[0] = new RWTButton("1");
		optionButtons[0].setFont(f);
		optionButtons[0].setRelativePosition(0.1f, 0.8f);
		optionButtons[0].setRelativeHeight(0.08f);
		optionButtons[0].setRelativeWidth(0.2f);
		addSelectableWidget(optionButtons[0], 0, 0);

		optionButtons[1] = new RWTButton("2");
		optionButtons[1].setFont(f);
		optionButtons[1].setRelativePosition(0.5f, 0.8f);
		optionButtons[1].setRelativeHeight(0.08f);
		optionButtons[1].setRelativeWidth(0.2f);
		addSelectableWidget(optionButtons[1], 1, 0);

		optionButtons[2] = new RWTButton("3");
		optionButtons[2].setFont(f);
		optionButtons[2].setRelativePosition(0.1f, 0.9f );
		optionButtons[2].setRelativeHeight(0.08f);
		optionButtons[2].setRelativeWidth(0.2f);
		addSelectableWidget(optionButtons[2], 0, 1);

		optionButtons[3] = new RWTButton("4");
		optionButtons[3].setFont(f);
		optionButtons[3].setRelativePosition(0.5f, 0.9f);
		optionButtons[3].setRelativeHeight(0.08f);
		optionButtons[3].setRelativeWidth(0.2f);
		addSelectableWidget(optionButtons[3], 1, 1);


		repaint();
	}


	public  void actionuke(int a) {

		seikai=a;
		if (seikai==1) {
			
		image2 = new RWTImage("data\\images\\m101.jpg");
		image2.setRelativePosition(0.5f, 0.5f);
		image2.setSize(100, 100);
		canvas.addWidget(image2);
		repaint();
		}
			seikai=0;
		repaint();
	}
	
	public void mon() {
		image = new RWTImage("data\\images\\quizhaikei.jpg");
		image.setRelativePosition(0.0f, -0.2f);
		image.setSize(1000, 1000);
		canvas.addWidget(image);
		
	}
	
	

	public void showOption(int n, String option) {
		optionButtons[n].setLabel(option);
	}

	@Override
	public void keyPressed(RWTVirtualKey key) {
		if (key.getVirtualKey() == RWTVirtualController.RIGHT) {
			cursorMoveRight();
			sentaku.play();
		} else if (key.getVirtualKey() == RWTVirtualController.LEFT) {
			cursorMoveLeft();
			sentaku.play();
		} else if (key.getVirtualKey() == RWTVirtualController.UP) {
			cursorMoveUp();
			sentaku.play();
		} else if (key.getVirtualKey() == RWTVirtualController.DOWN) {
			cursorMoveDown();
			sentaku.play();
		}
	}

	@Override
	public void keyReleased(RWTVirtualKey key) {
		if (key.getPlayer() == 0 && key.getVirtualKey() == RWTVirtualController.BUTTON_A) {
			RWTWidget selected = getSelectedWidget();
			kettei.play();
			for (int i = 0; i < 4; i++) {
				if (selected == optionButtons[i]) {
					scenario.fire(optionButtons[i].getLabel());
				}
			}
		}
	}

	@Override
	public void keyTyped(RWTVirtualKey key) {
	}
}
