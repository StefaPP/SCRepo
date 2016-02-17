package gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfByte;
import org.opencv.core.MatOfRect;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.objdetect.CascadeClassifier;
import org.opencv.videoio.VideoCapture;

/**
 * 
 * @author Stefan Popov Plavsic
 * @author Aleksandar Okiljevic
 */
public class FaceDetection extends JFrame {
	// /

	private DaemonThread myThread = null;
	int count = 0;
	VideoCapture webSource = null;
	Mat frame = new Mat();
	MatOfByte mem = new MatOfByte();
	CascadeClassifier faceDetector = new CascadeClassifier(
			FaceDetection.class.getResource("haarcascade_frontalface_alt_tree.xml").getPath().substring(1));
	MatOfRect faceDetections = new MatOfRect();
	public int random = 0;
	public int recX;
	public int recY;
	public int recW;
	public int recH;
	public boolean mozePoliticar = false;
	public boolean mozeGlumac = false;
	public boolean mozeFudbaler = false;

	// /
	public BufferedImage img = null;

	class DaemonThread implements Runnable {

		protected volatile boolean runnable = false;

		public void run() {
			synchronized (this) {
				while (runnable) {
					if (webSource.grab()) {
						try {

							webSource.retrieve(frame);
							Graphics g = jPanel1.getGraphics();
							faceDetector.detectMultiScale(frame, faceDetections);
							for (Rect rect : faceDetections.toArray()) {
								setRecX(rect.x);
								setRecY(rect.y);
								setRecW(rect.width);
								setRecH(rect.height);
							//	Imgproc.rectangle(frame, new Point(rect.x, rect.y),
									//	new Point(rect.x + rect.width, rect.y + rect.height), new Scalar(0, 255, 0));
								//
								

							}
							Imgcodecs.imencode(".bmp", frame, mem);
							Image im = ImageIO.read(new ByteArrayInputStream(mem.toArray()));
							BufferedImage buff = (BufferedImage) im;
							if (g.drawImage(buff, 0, 0, getWidth(), getHeight() - 150, 0, 0, buff.getWidth(),
									buff.getHeight(), null)) {
								if (runnable == false) {
									System.out.println("Paused ..... ");
									this.wait();
								} else {

									if (mozeFudbaler) {
										switch (random) {
										case 1:
											img = ImageIO.read(new File("files\\messi.png"));
											g.drawImage(img, getRecX() , getRecY() ,
													getRecW(), getRecH(), null);
											break;

										case 2:
											img = ImageIO.read(new File("files\\ronaldo.png"));
											g.drawImage(img, getRecX() +getRecW() / 3, getRecY() - getRecH() / 4,
													getRecW(), getRecH(), null);
											break;

										}
									}

									if (mozeGlumac == true) {
										switch (random) {
										case 1:
											
											img = ImageIO.read(new File("files\\sparta.png"));
											
											g.drawImage(img, getRecX() +getRecX()/5, getRecY() -((getRecH())/2)   ,
													getRecW() * 3 /2, getRecH()*3 /2, null);
											
											break;

										case 2:
											img = ImageIO.read(new File("files\\brad.png"));
											g.drawImage(img, getRecX() + getRecX() / 4, getRecY() - getRecH() / 2,
													getRecW()*4/3, getRecH()*4/3, null);
											break;
										case 3:
											img = ImageIO.read(new File("files\\smit.png"));
											g.drawImage(img, getRecX() + getRecX() / 4, getRecY() - getRecH() / 2,
													getRecW()*4/3, getRecH()*4/3, null);
											break;
										}
									}
									float scale = (float) 0.0;
									if (getRecX() < 50 && getRecY() < 50) {
										scale = (float) 2;
									}
									if (mozePoliticar == true) {
										switch (random) {
										case 1:
											img = ImageIO.read(new File("files\\obama.png"));
											
											    
												g.drawImage(img, getRecX() + getRecX()/4, getRecY() - getRecH() / 2,
														getRecW()*4/3, getRecH()*4/3, null);
												
												break;
											

										case 2:
											img = ImageIO.read(new File("files\\kim.png"));
											g.drawImage(img, getRecX() + getRecX()/4, getRecY() - getRecH() /2,
													getRecW()*3/2, getRecH()*3/2, null);
											break;
										case 3:
											img = ImageIO.read(new File("files\\putin.png"));
											g.drawImage(img, getRecX() + getRecX() / 4, getRecY() - getRecH() / 2,
													getRecW()*4/3, getRecH()*4/3, null);
											break;
										}
									}
								}
							}
						} catch (Exception ex) {
							System.out.println("Error");
						}
					}
				}
			}
		}
	}

	// ///////
	/**
	 * Creates new form FaceDetection
	 */
	public FaceDetection() {
		initComponents();
		Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
		this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);
		this.setResizable(false);
		System.out.println(FaceDetection.class.getResource("haarcascade_frontalface_alt.xml").getPath().substring(1));
	}

	/**
	 * This method is called from within the constructor to initialize the form.
	 * WARNING: Do NOT modify this code. The content of this method is always
	 * regenerated by the Form Editor.
	 */
	@SuppressWarnings("unchecked")
	// <editor-fold defaultstate="collapsed"
	// <editor-fold defaultstate="collapsed" desc="Generated
	// <editor-fold defaultstate="collapsed" desc="Generated Code">
	private void initComponents() {

		jPanel1 = new javax.swing.JPanel();
		jButton1 = new javax.swing.JButton();
		jButton2 = new javax.swing.JButton();
		jButton3 = new javax.swing.JButton();

		jButton5 = new javax.swing.JButton();
		jButton3.setEnabled(false);
		jButton5.setEnabled(false);
		setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

		javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
		jPanel1.setLayout(jPanel1Layout);
		jPanel1Layout.setHorizontalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 692, Short.MAX_VALUE));
		jPanel1Layout.setVerticalGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
				.addGap(0, 401, Short.MAX_VALUE));

		jButton1.setText("Start");
		jButton1.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton1ActionPerformed(evt);
			}
		});

		jButton2.setText("Pause");
		jButton2.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton2ActionPerformed(evt);
			}
		});

		jButton3.setText("Koji ste glumac???");
		jButton3.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton3ActionPerformed(evt);
			}
		});

		jButton5.setText("Koji ste politicar???");
		jButton5.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				jButton5ActionPerformed(evt);
			}
		});

		javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup()
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
						.addGroup(layout.createSequentialGroup().addContainerGap(92, Short.MAX_VALUE).addComponent(
								jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
								javax.swing.GroupLayout.PREFERRED_SIZE))
						.addGroup(layout.createSequentialGroup().addGap(74, 74, 74).addComponent(jButton1)
								.addGap(38, 38, 38).addComponent(jButton2).addGap(73, 73, 73)
								.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
										.addComponent(jButton5, javax.swing.GroupLayout.DEFAULT_SIZE, 169,
												Short.MAX_VALUE)
										.addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE,
												javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
				.addContainerGap()));
		layout.setVerticalGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
				.createSequentialGroup().addContainerGap()
				.addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
						javax.swing.GroupLayout.PREFERRED_SIZE)
				.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING).addGroup(layout
						.createSequentialGroup()
						.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 28, Short.MAX_VALUE)
						.addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
								.addComponent(jButton1).addComponent(jButton2))
						.addGap(48, 48, 48))
						.addGroup(layout.createSequentialGroup().addGap(18, 18, 18).addComponent(jButton3)
								.addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED,
										javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
								.addComponent(jButton5).addContainerGap()))));

		pack();
	}// </editor-fold>

	private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {
		random = randInt(1, 3);
		mozePoliticar = true;
		mozeGlumac = false;

	}

	private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {
		random = randInt(1, 3);
		mozePoliticar = false;
		mozeGlumac = true;
	}

	public static int randInt(int min, int max) {

		// Usually this can be a field rather than a method variable
		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	private void jButton2ActionPerformed(ActionEvent evt) {// GEN-FIRST:event_jButton2ActionPerformed
		myThread.runnable = false;
		jButton2.setEnabled(false); // activate start button
		jButton1.setEnabled(true); // deactivate stop button
		webSource.release();

	}// GEN-LAST:event_jButton2ActionPerformed

	private void jButton1ActionPerformed(ActionEvent evt) {// GEN-FIRST:event_jButton1ActionPerformed
		jButton3.setEnabled(true);
		jButton5.setEnabled(true);
		webSource = new VideoCapture(0);
		myThread = new DaemonThread();
		Thread t = new Thread(myThread);
		t.setDaemon(true);
		myThread.runnable = true;
		t.start();
		jButton1.setEnabled(false);
		jButton2.setEnabled(true);

	}// GEN-LAST:event_jButton1ActionPerformed

	/**
	 * @param args
	 *            the command line arguments
	 */
	public static void main(String args[]) {
		System.loadLibrary(Core.NATIVE_LIBRARY_NAME);

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (InstantiationException ex) {
			java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (IllegalAccessException ex) {
			java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			java.util.logging.Logger.getLogger(FaceDetection.class.getName()).log(java.util.logging.Level.SEVERE, null,
					ex);
		}
		// </editor-fold>

		/* Create and display the form */
		java.awt.EventQueue.invokeLater(new Runnable() {
			public void run() {
				new FaceDetection().setVisible(true);
			}
		});
	}

	public int getRecX() {
		return recX;
	}

	public void setRecX(int recX) {
		this.recX = recX;
	}

	public int getRecY() {
		return recY;
	}

	public void setRecY(int recY) {
		this.recY = recY;
	}

	public int getRecW() {
		return recW;
	}

	public void setRecW(int recW) {
		this.recW = recW;
	}

	public int getRecH() {
		return recH;
	}

	public void setRecH(int recH) {
		this.recH = recH;
	}

	// Variables declaration - do not modify
	private javax.swing.JButton jButton1;
	private javax.swing.JButton jButton2;
	private javax.swing.JButton jButton3;
	private javax.swing.JButton jButton4;
	private javax.swing.JButton jButton5;
	private javax.swing.JPanel jPanel1;
	// End of variables declaration
}
