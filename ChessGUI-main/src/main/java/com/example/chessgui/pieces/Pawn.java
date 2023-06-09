package com.example.chessgui.pieces;

import com.example.chessgui.PieceColor;
import com.example.chessgui.board.Board;
import com.example.chessgui.board.BoardStructure;
import com.example.chessgui.board.Move;
import java.util.ArrayList;
import java.util.List;

public class Pawn extends Piece {
    private final static int[] CANDIDATE_MOVE_COORDINATE = { 8, 16, 7, 9 };

    public Pawn(final int piecePosition, final PieceColor pieceColor, final boolean isFirstMove) {
        super(piecePosition, pieceColor, PieceType.PAWN, isFirstMove);
    }

    @Override
    public List<Move> calcLegalMoves(final Board board) {
        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_COORDINATE) {
            final int candidateDestinationCoordinate = this.piecePosition
                    + (this.pieceColor.getDirection() * candidateCoordinateOffset);

            if (!BoardStructure.isValidCoordinate(candidateDestinationCoordinate)) {
                continue;
            }

            if (candidateCoordinateOffset == 8 && !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                // TODO more work to do here(deal with promotions )!!!!!!!!!!!!
                legalMoves.add(new Move.pieceMove(board, this, candidateDestinationCoordinate));
            } else if (candidateCoordinateOffset == 16 && this.isFirstMove() &&
                    (BoardStructure.SECOND_ROW[this.piecePosition] && this.getPieceColor().isBlack()) ||
                    (BoardStructure.SEVENTH_ROW[this.piecePosition] && this.getPieceColor().isWhite())) {
                final int behindCandidateDestinationCoordinate = this.piecePosition
                        + (this.getPieceColor().getDirection() * 8);
                if (!board.getTile(behindCandidateDestinationCoordinate).isTileOccupied() &&
                        !board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    legalMoves.add(new Move.pieceMove(board, this, candidateDestinationCoordinate));
                }
            } else if (candidateCoordinateOffset == 7 &&
                    !((BoardStructure.EIGHTH_COLUMN[this.piecePosition] && this.pieceColor.isWhite()) ||
                            (BoardStructure.FIRST_COLUMN[this.piecePosition] && this.pieceColor.isBlack()))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        // TODO more work to do here(deal with promotions )!!!!!!!!!!!!
                        legalMoves.add(new Move.pieceMove(board, this, candidateDestinationCoordinate));
                    }
                }

            } else if (candidateCoordinateOffset == 9 &&
                    !((BoardStructure.FIRST_COLUMN[this.piecePosition] && this.pieceColor.isWhite()) ||
                            (BoardStructure.EIGHTH_COLUMN[this.piecePosition] && this.pieceColor.isBlack()))) {
                if (board.getTile(candidateDestinationCoordinate).isTileOccupied()) {
                    final Piece pieceOnCandidate = board.getTile(candidateDestinationCoordinate).getPiece();
                    if (this.pieceColor != pieceOnCandidate.getPieceColor()) {
                        // TODO more work to do here(deal with promotions )!!!!!!!!!!!!
                        legalMoves.add(new Move.pieceMove(board, this, candidateDestinationCoordinate));
                    }
                }
            }
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change
        // body of generated methods, choose Tools | Templates.
        return legalMoves;
    }
}
