package com.example.chessgui.pieces;

import java.util.*;

import com.example.chessgui.PieceColor;
import com.example.chessgui.board.Board;
import com.example.chessgui.board.BoardStructure;
import com.example.chessgui.board.Move;
import com.example.chessgui.board.Tile;

public final class Knight extends Piece {

    private final static int[] CANDIDATE_MOVE_VECTOR_COORDINATES = { -17, -15, -10, -6, 6, 10, 15, 17 };

    public Knight(final PieceColor pieceColor,
            final int piecePosition) {
        super(piecePosition, pieceColor, PieceType.KNIGHT, true);
    }

    public Knight(final PieceColor pieceColor,
            final int piecePosition,
            final boolean isFirstMove) {
        super(piecePosition, pieceColor, PieceType.KNIGHT, isFirstMove);
    }

    /*
     * @Override
     * public Collection<Move> calculateLegalMoves(final Board board) {
     * final List<Move> legalMoves = new ArrayList<>();
     * for (final int candidateDestinationCoordinate :
     * PRECOMPUTED_CANDIDATES.get(this.piecePosition)) {
     * final Piece pieceAtDestination =
     * board.getPiece(candidateDestinationCoordinate);
     * if (pieceAtDestination == null) {
     * legalMoves.add(new MajorMove(board, this, candidateDestinationCoordinate));
     * } else {
     * final Alliance pieceAtDestinationAllegiance =
     * pieceAtDestination.getPieceAllegiance();
     * if (this.pieceAlliance != pieceAtDestinationAllegiance) {
     * legalMoves.add(new MajorAttackMove(board, this,
     * candidateDestinationCoordinate,
     * pieceAtDestination));
     * }
     * }
     * }
     * return Collections.unmodifiableList(legalMoves);
     * }
     */
    @Override
    public List<Move> calcLegalMoves(Board board) {

        final List<Move> legalMoves = new ArrayList<>();

        for (final int candidateCoordinateOffset : CANDIDATE_MOVE_VECTOR_COORDINATES) {
            int candidateDestinationCoordinate = this.piecePosition;

            while (BoardStructure.isValidCoordinate(candidateDestinationCoordinate)) {
                if (isFirstColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isEighthColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)
                        || isSecondColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset) ||
                        isSeventhColumnExclusion(candidateDestinationCoordinate, candidateCoordinateOffset)) {
                    break;
                }
                candidateDestinationCoordinate += candidateCoordinateOffset;

                if (BoardStructure.isValidCoordinate(candidateDestinationCoordinate)) {
                    final Tile candidateDestinationTile = board.getTile(candidateDestinationCoordinate);

                    if (!candidateDestinationTile.isTileOccupied()) {
                        legalMoves.add(new Move.pieceMove(board, this, candidateDestinationCoordinate));
                    } else {
                        final Piece pieceAtDestination = candidateDestinationTile.getPiece();
                        final PieceColor piece_color = pieceAtDestination.getPieceColor();
                        if (this.pieceColor != piece_color) {
                            legalMoves.add(new Move.attackMove(board, pieceAtDestination,
                                    candidateDestinationCoordinate, pieceAtDestination));
                        }
                        break;
                    }
                }
            }
        }
        // throw new UnsupportedOperationException("Not supported yet."); //To change
        // body of generated methods, choose Tools | Templates.
        return legalMoves;
    }

    /*
     * @Override
     * public int locationBonus() {
     * return this.pieceAlliance.knightBonus(this.piecePosition);
     * }
     * 
     * @Override
     * public Knight movePiece(final Move move) {
     * return
     * PieceUtils.INSTANCE.getMovedKnight(move.getMovedPiece().getPieceAllegiance(),
     * move.getDestinationCoordinate());
     * }
     */

    @Override
    public String toString() {
        return this.pieceType.toString();
    }

    private static boolean isFirstColumnExclusion(final int currentPosition,
            final int candidateOffset) {
        return BoardStructure.FIRST_COLUMN[currentPosition] && ((candidateOffset == -17) ||
                (candidateOffset == -10) || (candidateOffset == 6) || (candidateOffset == 15));
    }

    private static boolean isSecondColumnExclusion(final int currentPosition,
            final int candidateOffset) {
        return BoardStructure.SECOND_COLUMN[currentPosition]
                && ((candidateOffset == -10) || (candidateOffset == 6));
    }

    private static boolean isSeventhColumnExclusion(final int currentPosition,
            final int candidateOffset) {
        return BoardStructure.SEVENTH_COLUMN[currentPosition]
                && ((candidateOffset == -6) || (candidateOffset == 10));
    }

    private static boolean isEighthColumnExclusion(final int currentPosition,
            final int candidateOffset) {
        return BoardStructure.EIGHTH_COLUMN[currentPosition]
                && ((candidateOffset == -15) || (candidateOffset == -6) ||
                        (candidateOffset == 10) || (candidateOffset == 17));
    }

}