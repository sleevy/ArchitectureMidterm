package edu.georgiasouthern.cr04956.architecturemidterm;

import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TicTacToeBoard board;
    ImageView[] boardImages;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        board = new TicTacToeBoard();
        boardImages = new ImageView[9];

        boardImages[0] = (ImageView) findViewById(R.id.board0);
        boardImages[1] = (ImageView) findViewById(R.id.board1);
        boardImages[2] = (ImageView) findViewById(R.id.board2);
        boardImages[3] = (ImageView) findViewById(R.id.board3);
        boardImages[4] = (ImageView) findViewById(R.id.board4);
        boardImages[5] = (ImageView) findViewById(R.id.board5);
        boardImages[6] = (ImageView) findViewById(R.id.board6);
        boardImages[7] = (ImageView) findViewById(R.id.board7);
        boardImages[8] = (ImageView) findViewById(R.id.board8);

        for(int i = 0; i < boardImages.length; i++) {
            final int id = i;
            boardImages[i].setOnClickListener( new View.OnClickListener() {
                public void onClick(View v) {
                    ImageView img = (ImageView) v;
                    board.tryToPlacePiece(id);
                    setImageBasedOnPiece(img, board.getPiece(id));
                    setTextBasedOnTurnOrWin();
                }
            });
        }

        Button restartButton = (Button) findViewById(R.id.btnRestart);
        restartButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                board.initializeBoard();
                setTextBasedOnTurnOrWin();
                setAllBoardImages();
            }
        });
    }

    private void setAllBoardImages() {
        for(int id = 0; id < boardImages.length; id++) {
            setImageBasedOnPiece(boardImages[id], board.getPiece(id));
        }
    }

    private void setTextBasedOnTurnOrWin() {
        TextView txt = (TextView) findViewById(R.id.txtStatus);
        int textId = R.string.error_text;
        boolean hasWin = board.hasWinner();
        if(hasWin) {
            TicTacToeBoard.TicTacToePiece winner = board.getWinner();
            switch (winner) {
                case X_PIECE: textId = R.string.x_victory;
                    break;
                case O_PIECE: textId = R.string.o_victory;
                    break;
                case NO_PIECE: textId = R.string.no_victory;
                    break;
            }

        } else { //no winner, set turn text
            if(board.isXTurn()) {
                textId = R.string.x_turn;
            } else {
                textId = R.string.o_turn;
            }
        }
        txt.setText(textId);
    }

    private void setImageBasedOnPiece(ImageView v, TicTacToeBoard.TicTacToePiece piece) {
        int imgId = R.drawable.errBoard;

        switch (piece) {
            case X_PIECE: imgId = R.drawable.xSymbol;
                break;
            case O_PIECE: imgId = R.drawable.oSymbol;
                break;
            case NO_PIECE: imgId = R.drawable.emptyBoard;
                break;
        }

        v.setImageResource(imgId);
    }
}
