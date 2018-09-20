package Core;

import java.util.logging.FileHandler;
import java.util.logging.Logger;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.stage.Stage; 
import javafx.scene.layout.Pane; 
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.layout.FlowPane;
import javafx.geometry.Orientation;
import javafx.scene.image.ImageView;


//code inspired from; https://github.com/dylanparsons/BlackJack/blob/master/blackjack.java

public class BlackjackGUI extends Application{
	
	private static Logger logger = Logger.getLogger("BlackjackGUI.class");
	//FileHandler fh = new FileHandler("className.log");   
	
    FlowPane playerCards = new FlowPane(Orientation.HORIZONTAL);
    FlowPane dealerCards = new FlowPane(Orientation.HORIZONTAL);
    
    FlowPane playerSplitCards = new FlowPane(Orientation.HORIZONTAL);
    FlowPane dealerSplitCards = new FlowPane(Orientation.HORIZONTAL);
    
	Label totalPlayerLabel; 
	Label totalDealerLabel;
	Label totalDealerSplitLabel; 
	Label totalPlayerSplitLabel;

    //Label dealerLabel = new Label("Dealer"); 
    //Label playerLabel = new Label("Player"); 
    

	private Button confirmStartGame; 
	private Button confirmHit; 
	private Button confirmStand; 
	private Button confirmSplit; 


	Label status; 	
	
	
	private GameMaster game = new GameMaster(); 
	
	public static final String [] input = {"CA", "C2", "C3", "C4", "C5", "C6", "C7", "C8", "C9", "C10", "CJ", "CQ", "CK",
			"DA", "D2", "D3", "D4", "D5", "D6", "D7", "D8", "D9", "D10", "DJ", "DQ", "DK",
			"HA", "H2", "H3", "H4", "H5", "H6", "H7", "H8", "H9", "H10", "HJ", "HQ", "HK",
			"SA", "S2", "S3", "S4", "S5", "S6", "S7", "S8", "S9", "S10", "SJ", "SQ", "SK"};
	
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		// TODO Auto-generated method stub
		initUI(primaryStage);
	}
	
	private void initUI(Stage primaryStage) {
		// TODO Auto-generated method stub
		Pane canvas  = new Pane();
		//canvas.setStyle("-fx-background-color: black"); 
		addControlsToCanvas(canvas);

		//Scene scene = new Scene(canvas, 550, 550);
		Scene scene = new Scene(canvas, 400, 400);

		primaryStage.setScene(scene);
		primaryStage.setTitle("Simple BlackJack App");
		primaryStage.show();
	}

	private void addControlsToCanvas(Pane canvas) {
		// TODO Auto-generated method stub
		//int row1 = 20;// due to repition of locations 
		//int row2 = 60;
		
		Label label = new Label("Simple Blackjack"); 
		label.setFont(Font.font("Serif", FontWeight.BOLD, 20));
		label.relocate(20, 20);
		

		playerCards.setMaxWidth(155);
		playerCards.relocate(200, 150);
		
		dealerCards.setMaxWidth(155);
		dealerCards.relocate(20, 150);
		
		playerSplitCards.setMaxWidth(155);
		playerSplitCards.relocate(200, 400);
		
		dealerSplitCards.setMaxWidth(155);
		dealerSplitCards.relocate(20, 400);

		
	    Label dealerLabel = new Label("Dealer");
	    dealerLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    dealerLabel.relocate(20, 100);
		
	    Label playerLabel = new Label("Player");
	    playerLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    playerLabel.relocate(200, 100);
	    
	    Label dealerSplitLabel = new Label("Dealer Split");
	    dealerSplitLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    dealerSplitLabel.relocate(20, 350);
	    dealerSplitLabel.setVisible(false);
		
	    Label playerSplitLabel = new Label("Player Split");
	    playerSplitLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    playerSplitLabel.relocate(200, 350);
	    playerSplitLabel.setVisible(false);

	    
	    totalPlayerLabel = new Label();
	    totalPlayerLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    totalPlayerLabel.relocate(200, 120);
	    
	    totalDealerLabel = new Label();
	    totalDealerLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    totalDealerLabel.relocate(20, 120);
	    
	    totalPlayerSplitLabel = new Label();
	    totalPlayerSplitLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    totalPlayerSplitLabel.relocate(200, 240);
	    totalPlayerSplitLabel.setVisible(false);

	    
	    totalDealerSplitLabel = new Label();
	    totalDealerSplitLabel.setFont(Font.font("Serif", FontWeight.NORMAL, 17));
	    totalDealerSplitLabel.relocate(20, 240);
	    totalDealerSplitLabel.setVisible(false);

	    

	    status = new Label();
	    status.setFont(Font.font("Serif", FontWeight.BOLD, 25));
	    //status.relocate(365, 250);
	    status.relocate(160, 365);

	    
		confirmStartGame = new Button("Start Game");
		confirmStartGame.setMaxWidth(100);
		confirmStartGame.relocate(300, 30);
		
		confirmHit = new Button("Hit");
		confirmHit.setMaxWidth(100);
		confirmHit.relocate(300, 70);
		confirmHit.setDisable(true);
		
		confirmStand = new Button("Stand");
		confirmStand.setMaxWidth(100);
		confirmStand.relocate(300, 110);
		confirmStand.setDisable(true);
		
		confirmSplit = new Button("Split?");
		confirmSplit.setMaxWidth(100);
		confirmSplit.relocate(300, 140);
		confirmSplit.setDisable(true);
		confirmSplit.setVisible(false);

		
		setConfirmButtonHandler();
		
		canvas.getChildren().addAll(label, confirmStartGame, confirmHit, status, totalDealerSplitLabel, totalPlayerSplitLabel,
				confirmStand, playerCards, dealerCards, dealerLabel, playerLabel, totalPlayerLabel, totalDealerLabel,
				playerSplitLabel, dealerSplitLabel, confirmSplit, playerSplitCards, dealerSplitCards);
	}

	private void setConfirmButtonHandler() {
		// TODO Auto-generated method stub
		confirmStartGame.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				game.deck.createDefaultDeck(input); 
				game.deck.shuffleDeck();
				logger.info("Deck has been created and shuffled");
				drawCard(game.player.getHand(), playerCards,totalPlayerLabel );
				drawCard(game.player.getHand(), playerCards,totalPlayerLabel );
				drawCard(game.dealer.getHand(), dealerCards,totalDealerLabel );
				drawCard(game.dealer.getHand(), dealerCards,totalDealerLabel );
				confirmStartGame.setDisable(true);	
				game.player.getHand().turn = true; 
				
				game.checkBlackjack(game.player.getHand(), game.dealer.getHand());
				if(game.player.getHand().isSplit()) {
					confirmSplit.setDisable(false);
				}
				
				confirmHit.setDisable(false);
				confirmStand.setDisable(false);
			
				if(game.playerIsWinner)
                    status.setText("You've won"); 
				else 
					if(game.playerIsWinner)
	                    status.setText("You've lost"); 
				
				if(game.playerIsWinner || game.dealerIsWinner) {
					confirmStand.setDisable(true);
					confirmHit.setDisable(true);
				}
			}
	
		});
		
		confirmHit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(game.player.getHand().turn) {
					drawCard(game.player.getHand(), playerCards,totalPlayerLabel );
					//game.checkBust(); 
				}
				/*else if(game.player.getSplitHand().turn) {
					drawCard(game.player.getSplitHand(), playerSplitCards,totalPlayerSplitLabel );
				}*/
				
				if(game.playerIsWinner)
                    status.setText("You've won"); 
				else 
					if(game.playerIsWinner)
	                    status.setText("You've lost"); 
				
				if(game.playerIsWinner || game.dealerIsWinner) {
					confirmStand.setDisable(true);
					confirmHit.setDisable(true);
				}
				
			
			}
	
		});
		
		confirmStand.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				// TODO Auto-generated method stub
				if(game.player.getHand().turn) {
					game.player.getHand().turn = false; 
					/*if(game.splitting) {
						game.player.getSplitHand().turn = true; 
						drawCard(game.player.getSplitHand(), playerSplitCards,totalPlayerSplitLabel );

					}*/
				//if(game.player.getSplitHand().turn == false) {
					game.dealer.getHand().turn = true; 
			
					while(game.dealer.getHand().countTotal() < 17 || (game.dealer.getHand().isSoft())) {
						drawCard(game.dealer.getHand(), dealerCards,totalDealerLabel );
						if(game.dealer.getHand().isBust())break;
					}
				//}
				
			}
				

				game.checkWinner();
				if(game.playerIsWinner)
                    status.setText("You've won"); 
				else if(game.dealerIsWinner)
	                    status.setText("You've lost"); 

				
				if(game.playerIsWinner || game.dealerIsWinner) {
					confirmStand.setDisable(true);
					confirmHit.setDisable(true);
				}
				/*else if(game.player.getSplitHand().turn)
					drawCard(game.dealer.getHand(), dealerCards,totalDealerLabel );*/
			
			}
	
		});
		
		/*confirmSplit.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				game.splitting = true; 
				game.player.getSplitHand().addCard(game.player.getHand().split()); 
				drawCard(game.player.getHand(), playerCards,totalPlayerLabel );

			}
		});*/
	}

	protected void drawCard(Hand hand, FlowPane pane, Label totalLabel) {
		// TODO Auto-generated method stub
		try {

		Card tempCard = game.deck.drawCard(); 
		
		ImageView img = new ImageView(tempCard.getCardImage());
		img.setFitHeight(50);
		img.setFitWidth(75);
		logger.info(tempCard.getInput()+ " "+tempCard.getValue());
		hand.addCard(tempCard);
		

		//	img.setVisible(true);
			pane.getChildren().add(img);

		game.checkBust(); 
		
			
		totalLabel.setText(Integer.toString(hand.countTotal())); 
		if(game.playerIsWinner)
            status.setText("You've won"); 
		else if(game.dealerIsWinner)
                status.setText("You've lost"); 

		
		if(game.playerIsWinner || game.dealerIsWinner) {
			confirmStand.setDisable(true);
			confirmHit.setDisable(true);
		}
		}catch(Exception io) {
			logger.info(io.getMessage());
		}
	}
}
