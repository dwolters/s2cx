/* Generated By:JJTree&JavaCC: Do not edit this line. SQLXMLParserTokenManager.java */
package de.upb.s2cx.parser;
import de.upb.s2cx.parser.sql.*;
import de.upb.s2cx.parser.sql.xml.*;
import gnu.trove.stack.TByteStack;
import gnu.trove.stack.array.TByteArrayStack;

/** Token Manager. */
public class SQLXMLParserTokenManager implements SQLXMLParserConstants
{

  /** Debug output. */
  public  java.io.PrintStream debugStream = System.out;
  /** Set debug output. */
  public  void setDebugStream(java.io.PrintStream ds) { debugStream = ds; }
private final int jjStopStringLiteralDfa_0(int pos, long active0)
{
   switch (pos)
   {
      case 0:
         if ((active0 & 0xf800fffff400L) != 0L)
         {
            jjmatchedKind = 5;
            return -1;
         }
         return -1;
      case 1:
         if ((active0 & 0xf800fffff400L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 2:
         if ((active0 & 0x5800fffff000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 3:
         if ((active0 & 0x800c3fdf000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 4:
         if ((active0 & 0xc3e1d000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 5:
         if ((active0 & 0x83e19000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 6:
         if ((active0 & 0x1e18000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 7:
         if ((active0 & 0x1e18000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 8:
         if ((active0 & 0x1e00000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 9:
         if ((active0 & 0x600000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 10:
         if ((active0 & 0x400000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      case 11:
         if ((active0 & 0x400000L) != 0L)
         {
            if (jjmatchedPos == 0)
            {
               jjmatchedKind = 5;
               jjmatchedPos = 0;
            }
            return -1;
         }
         return -1;
      default :
         return -1;
   }
}
private final int jjStartNfa_0(int pos, long active0)
{
   return jjMoveNfa_0(jjStopStringLiteralDfa_0(pos, active0), pos + 1);
}
private int jjStopAtPos(int pos, int kind)
{
   jjmatchedKind = kind;
   jjmatchedPos = pos;
   return pos + 1;
}
private int jjMoveStringLiteralDfa0_0()
{
   switch(curChar)
   {
      case 40:
         return jjStopAtPos(0, 35);
      case 41:
         return jjStopAtPos(0, 36);
      case 42:
         return jjStopAtPos(0, 48);
      case 43:
         return jjStopAtPos(0, 50);
      case 44:
         return jjStopAtPos(0, 11);
      case 45:
         return jjStopAtPos(0, 51);
      case 46:
         return jjStopAtPos(0, 34);
      case 47:
         return jjStopAtPos(0, 49);
      case 60:
         jjmatchedKind = 40;
         return jjMoveStringLiteralDfa1_0(0x24000000000L);
      case 61:
         return jjStopAtPos(0, 37);
      case 62:
         jjmatchedKind = 39;
         return jjMoveStringLiteralDfa1_0(0x40000000000L);
      case 65:
         return jjMoveStringLiteralDfa1_0(0x100008020400L);
      case 67:
         return jjMoveStringLiteralDfa1_0(0x40000000L);
      case 68:
         return jjMoveStringLiteralDfa1_0(0x140000L);
      case 69:
         return jjMoveStringLiteralDfa1_0(0x80000000L);
      case 70:
         return jjMoveStringLiteralDfa1_0(0x2000L);
      case 71:
         return jjMoveStringLiteralDfa1_0(0x10000L);
      case 73:
         return jjMoveStringLiteralDfa1_0(0x800000000000L);
      case 76:
         return jjMoveStringLiteralDfa1_0(0x80000000000L);
      case 77:
         return jjMoveStringLiteralDfa1_0(0x30000000L);
      case 78:
         return jjMoveStringLiteralDfa1_0(0x400000080000L);
      case 79:
         return jjMoveStringLiteralDfa1_0(0x200000008000L);
      case 83:
         return jjMoveStringLiteralDfa1_0(0x4001000L);
      case 87:
         return jjMoveStringLiteralDfa1_0(0x4000L);
      case 88:
         return jjMoveStringLiteralDfa1_0(0x3e00000L);
      default :
         return jjMoveNfa_0(0, 0);
   }
}
private int jjMoveStringLiteralDfa1_0(long active0)
{
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(0, active0);
      return 1;
   }
   switch(curChar)
   {
      case 61:
         if ((active0 & 0x20000000000L) != 0L)
            return jjStopAtPos(1, 41);
         else if ((active0 & 0x40000000000L) != 0L)
            return jjStopAtPos(1, 42);
         break;
      case 62:
         if ((active0 & 0x4000000000L) != 0L)
            return jjStopAtPos(1, 38);
         break;
      case 65:
         return jjMoveStringLiteralDfa2_0(active0, 0x20180000L);
      case 69:
         return jjMoveStringLiteralDfa2_0(active0, 0x41000L);
      case 72:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000L);
      case 73:
         return jjMoveStringLiteralDfa2_0(active0, 0x80010000000L);
      case 77:
         return jjMoveStringLiteralDfa2_0(active0, 0x3e00000L);
      case 78:
         if ((active0 & 0x800000000000L) != 0L)
            return jjStopAtPos(1, 47);
         return jjMoveStringLiteralDfa2_0(active0, 0x100000000000L);
      case 79:
         return jjMoveStringLiteralDfa2_0(active0, 0x400040000000L);
      case 82:
         if ((active0 & 0x200000000000L) != 0L)
         {
            jjmatchedKind = 45;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x1a000L);
      case 83:
         if ((active0 & 0x400L) != 0L)
         {
            jjmatchedKind = 10;
            jjmatchedPos = 1;
         }
         return jjMoveStringLiteralDfa2_0(active0, 0x20000L);
      case 85:
         return jjMoveStringLiteralDfa2_0(active0, 0x4000000L);
      case 86:
         return jjMoveStringLiteralDfa2_0(active0, 0x8000000L);
      case 88:
         return jjMoveStringLiteralDfa2_0(active0, 0x80000000L);
      default :
         break;
   }
   return jjStartNfa_0(0, active0);
}
private int jjMoveStringLiteralDfa2_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(0, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(1, active0);
      return 2;
   }
   switch(curChar)
   {
      case 67:
         if ((active0 & 0x20000L) != 0L)
            return jjStopAtPos(2, 17);
         break;
      case 68:
         if ((active0 & 0x100000000000L) != 0L)
            return jjStopAtPos(2, 44);
         return jjMoveStringLiteralDfa3_0(active0, 0x8000L);
      case 69:
         return jjMoveStringLiteralDfa3_0(active0, 0x4000L);
      case 71:
         if ((active0 & 0x8000000L) != 0L)
            return jjStopAtPos(2, 27);
         break;
      case 73:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000L);
      case 75:
         return jjMoveStringLiteralDfa3_0(active0, 0x80000000000L);
      case 76:
         return jjMoveStringLiteralDfa3_0(active0, 0x3e01000L);
      case 77:
         if ((active0 & 0x4000000L) != 0L)
            return jjStopAtPos(2, 26);
         return jjMoveStringLiteralDfa3_0(active0, 0x80000L);
      case 78:
         if ((active0 & 0x10000000L) != 0L)
            return jjStopAtPos(2, 28);
         break;
      case 79:
         return jjMoveStringLiteralDfa3_0(active0, 0x12000L);
      case 83:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000L);
      case 84:
         if ((active0 & 0x400000000000L) != 0L)
            return jjStopAtPos(2, 46);
         return jjMoveStringLiteralDfa3_0(active0, 0x100000L);
      case 85:
         return jjMoveStringLiteralDfa3_0(active0, 0x40000000L);
      case 88:
         if ((active0 & 0x20000000L) != 0L)
            return jjStopAtPos(2, 29);
         break;
      default :
         break;
   }
   return jjStartNfa_0(1, active0);
}
private int jjMoveStringLiteralDfa3_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(1, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(2, active0);
      return 3;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa4_0(active0, 0x2400000L);
      case 67:
         if ((active0 & 0x40000L) != 0L)
            return jjStopAtPos(3, 18);
         return jjMoveStringLiteralDfa4_0(active0, 0x1000000L);
      case 69:
         if ((active0 & 0x80000L) != 0L)
            return jjStopAtPos(3, 19);
         else if ((active0 & 0x100000L) != 0L)
            return jjStopAtPos(3, 20);
         else if ((active0 & 0x80000000000L) != 0L)
            return jjStopAtPos(3, 43);
         return jjMoveStringLiteralDfa4_0(active0, 0x209000L);
      case 70:
         return jjMoveStringLiteralDfa4_0(active0, 0x800000L);
      case 77:
         if ((active0 & 0x2000L) != 0L)
            return jjStopAtPos(3, 13);
         break;
      case 78:
         return jjMoveStringLiteralDfa4_0(active0, 0x40000000L);
      case 82:
         return jjMoveStringLiteralDfa4_0(active0, 0x4000L);
      case 83:
         return jjMoveStringLiteralDfa4_0(active0, 0x80000000L);
      case 85:
         return jjMoveStringLiteralDfa4_0(active0, 0x10000L);
      default :
         break;
   }
   return jjStartNfa_0(2, active0);
}
private int jjMoveStringLiteralDfa4_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(2, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(3, active0);
      return 4;
   }
   switch(curChar)
   {
      case 67:
         return jjMoveStringLiteralDfa5_0(active0, 0x1000L);
      case 69:
         if ((active0 & 0x4000L) != 0L)
            return jjStopAtPos(4, 14);
         break;
      case 71:
         return jjMoveStringLiteralDfa5_0(active0, 0x2000000L);
      case 76:
         return jjMoveStringLiteralDfa5_0(active0, 0x200000L);
      case 79:
         return jjMoveStringLiteralDfa5_0(active0, 0x1800000L);
      case 80:
         return jjMoveStringLiteralDfa5_0(active0, 0x10000L);
      case 82:
         return jjMoveStringLiteralDfa5_0(active0, 0x8000L);
      case 84:
         if ((active0 & 0x40000000L) != 0L)
            return jjStopAtPos(4, 30);
         return jjMoveStringLiteralDfa5_0(active0, 0x80400000L);
      default :
         break;
   }
   return jjStartNfa_0(3, active0);
}
private int jjMoveStringLiteralDfa5_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(3, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(4, active0);
      return 5;
   }
   switch(curChar)
   {
      case 32:
         return jjMoveStringLiteralDfa6_0(active0, 0x18000L);
      case 69:
         return jjMoveStringLiteralDfa6_0(active0, 0x200000L);
      case 71:
         if ((active0 & 0x2000000L) != 0L)
            return jjStopAtPos(5, 25);
         break;
      case 78:
         return jjMoveStringLiteralDfa6_0(active0, 0x1000000L);
      case 82:
         return jjMoveStringLiteralDfa6_0(active0, 0x800000L);
      case 83:
         if ((active0 & 0x80000000L) != 0L)
            return jjStopAtPos(5, 31);
         break;
      case 84:
         if ((active0 & 0x1000L) != 0L)
            return jjStopAtPos(5, 12);
         return jjMoveStringLiteralDfa6_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(4, active0);
}
private int jjMoveStringLiteralDfa6_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(4, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(5, active0);
      return 6;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa7_0(active0, 0x18000L);
      case 67:
         return jjMoveStringLiteralDfa7_0(active0, 0x1000000L);
      case 69:
         return jjMoveStringLiteralDfa7_0(active0, 0x800000L);
      case 77:
         return jjMoveStringLiteralDfa7_0(active0, 0x200000L);
      case 82:
         return jjMoveStringLiteralDfa7_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(5, active0);
}
private int jjMoveStringLiteralDfa7_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(5, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(6, active0);
      return 7;
   }
   switch(curChar)
   {
      case 65:
         return jjMoveStringLiteralDfa8_0(active0, 0x1000000L);
      case 69:
         return jjMoveStringLiteralDfa8_0(active0, 0x200000L);
      case 73:
         return jjMoveStringLiteralDfa8_0(active0, 0x400000L);
      case 83:
         return jjMoveStringLiteralDfa8_0(active0, 0x800000L);
      case 89:
         if ((active0 & 0x8000L) != 0L)
            return jjStopAtPos(7, 15);
         else if ((active0 & 0x10000L) != 0L)
            return jjStopAtPos(7, 16);
         break;
      default :
         break;
   }
   return jjStartNfa_0(6, active0);
}
private int jjMoveStringLiteralDfa8_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(6, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(7, active0);
      return 8;
   }
   switch(curChar)
   {
      case 66:
         return jjMoveStringLiteralDfa9_0(active0, 0x400000L);
      case 78:
         return jjMoveStringLiteralDfa9_0(active0, 0x200000L);
      case 84:
         if ((active0 & 0x800000L) != 0L)
            return jjStopAtPos(8, 23);
         else if ((active0 & 0x1000000L) != 0L)
            return jjStopAtPos(8, 24);
         break;
      default :
         break;
   }
   return jjStartNfa_0(7, active0);
}
private int jjMoveStringLiteralDfa9_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(7, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(8, active0);
      return 9;
   }
   switch(curChar)
   {
      case 84:
         if ((active0 & 0x200000L) != 0L)
            return jjStopAtPos(9, 21);
         break;
      case 85:
         return jjMoveStringLiteralDfa10_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(8, active0);
}
private int jjMoveStringLiteralDfa10_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(8, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(9, active0);
      return 10;
   }
   switch(curChar)
   {
      case 84:
         return jjMoveStringLiteralDfa11_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(9, active0);
}
private int jjMoveStringLiteralDfa11_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(9, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(10, active0);
      return 11;
   }
   switch(curChar)
   {
      case 69:
         return jjMoveStringLiteralDfa12_0(active0, 0x400000L);
      default :
         break;
   }
   return jjStartNfa_0(10, active0);
}
private int jjMoveStringLiteralDfa12_0(long old0, long active0)
{
   if (((active0 &= old0)) == 0L)
      return jjStartNfa_0(10, old0);
   try { curChar = input_stream.readChar(); }
   catch(java.io.IOException e) {
      jjStopStringLiteralDfa_0(11, active0);
      return 12;
   }
   switch(curChar)
   {
      case 83:
         if ((active0 & 0x400000L) != 0L)
            return jjStopAtPos(12, 22);
         break;
      default :
         break;
   }
   return jjStartNfa_0(11, active0);
}
private int jjMoveNfa_0(int startState, int curPos)
{
   int startsAt = 0;
   jjnewStateCnt = 10;
   int i = 1;
   jjstateSet[0] = startState;
   int kind = 0x7fffffff;
   for (;;)
   {
      if (++jjround == 0x7fffffff)
         ReInitRounds();
      if (curChar < 64)
      {
         long l = 1L << curChar;
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x3ff000000000000L & l) != 0L)
                  {
                     if (kind > 6)
                        kind = 6;
                     jjCheckNAdd(9);
                  }
                  else if (curChar == 34)
                     jjCheckNAdd(7);
                  else if (curChar == 39)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 3:
                  if (curChar == 39)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 4:
                  if ((0x3ff202900000000L & l) != 0L)
                     jjCheckNAddTwoStates(4, 5);
                  break;
               case 5:
                  if (curChar == 39 && kind > 32)
                     kind = 32;
                  break;
               case 6:
                  if (curChar == 34)
                     jjCheckNAdd(7);
                  break;
               case 7:
                  if ((0x3ff000000000000L & l) != 0L)
                     jjCheckNAddTwoStates(7, 8);
                  break;
               case 8:
                  if (curChar == 34 && kind > 33)
                     kind = 33;
                  break;
               case 9:
                  if ((0x3ff000000000000L & l) == 0L)
                     break;
                  if (kind > 6)
                     kind = 6;
                  jjCheckNAdd(9);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else if (curChar < 128)
      {
         long l = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               case 0:
                  if ((0x7fffffe07fffffeL & l) != 0L)
                  {
                     if (kind > 5)
                        kind = 5;
                  }
                  if ((0x7fffffeL & l) != 0L)
                  {
                     if (kind > 8)
                        kind = 8;
                  }
                  else if ((0x7fffffe00000000L & l) != 0L)
                  {
                     if (kind > 7)
                        kind = 7;
                  }
                  break;
               case 1:
                  if ((0x7fffffe00000000L & l) != 0L && kind > 7)
                     kind = 7;
                  break;
               case 2:
                  if ((0x7fffffeL & l) != 0L && kind > 8)
                     kind = 8;
                  break;
               case 4:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjAddStates(0, 1);
                  break;
               case 7:
                  if ((0x7fffffe87fffffeL & l) != 0L)
                     jjAddStates(2, 3);
                  break;
               default : break;
            }
         } while(i != startsAt);
      }
      else
      {
         int i2 = (curChar & 0xff) >> 6;
         long l2 = 1L << (curChar & 077);
         do
         {
            switch(jjstateSet[--i])
            {
               default : break;
            }
         } while(i != startsAt);
      }
      if (kind != 0x7fffffff)
      {
         jjmatchedKind = kind;
         jjmatchedPos = curPos;
         kind = 0x7fffffff;
      }
      ++curPos;
      if ((i = jjnewStateCnt) == (startsAt = 10 - (jjnewStateCnt = startsAt)))
         return curPos;
      try { curChar = input_stream.readChar(); }
      catch(java.io.IOException e) { return curPos; }
   }
}
static final int[] jjnextStates = {
   4, 5, 7, 8, 
};

/** Token literal values. */
public static final String[] jjstrLiteralImages = {
"", null, null, null, null, null, null, null, null, null, "\101\123", "\54", 
"\123\105\114\105\103\124", "\106\122\117\115", "\127\110\105\122\105", "\117\122\104\105\122\40\102\131", 
"\107\122\117\125\120\40\102\131", "\101\123\103", "\104\105\123\103", "\116\101\115\105", "\104\101\124\105", 
"\130\115\114\105\114\105\115\105\116\124", "\130\115\114\101\124\124\122\111\102\125\124\105\123", 
"\130\115\114\106\117\122\105\123\124", "\130\115\114\103\117\116\103\101\124", "\130\115\114\101\107\107", 
"\123\125\115", "\101\126\107", "\115\111\116", "\115\101\130", "\103\117\125\116\124", 
"\105\130\111\123\124\123", null, null, "\56", "\50", "\51", "\75", "\74\76", "\76", "\74", "\74\75", 
"\76\75", "\114\111\113\105", "\101\116\104", "\117\122", "\116\117\124", "\111\116", 
"\52", "\57", "\53", "\55", };

/** Lexer state names. */
public static final String[] lexStateNames = {
   "DEFAULT",
};
static final long[] jjtoToken = {
   0xfffffffffffe1L, 
};
static final long[] jjtoSkip = {
   0x1eL, 
};
protected SimpleCharStream input_stream;
private final int[] jjrounds = new int[10];
private final int[] jjstateSet = new int[20];
protected char curChar;
/** Constructor. */
public SQLXMLParserTokenManager(SimpleCharStream stream){
   if (SimpleCharStream.staticFlag)
      throw new Error("ERROR: Cannot use a static CharStream class with a non-static lexical analyzer.");
   input_stream = stream;
}

/** Constructor. */
public SQLXMLParserTokenManager(SimpleCharStream stream, int lexState){
   this(stream);
   SwitchTo(lexState);
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream)
{
   jjmatchedPos = jjnewStateCnt = 0;
   curLexState = defaultLexState;
   input_stream = stream;
   ReInitRounds();
}
private void ReInitRounds()
{
   int i;
   jjround = 0x80000001;
   for (i = 10; i-- > 0;)
      jjrounds[i] = 0x80000000;
}

/** Reinitialise parser. */
public void ReInit(SimpleCharStream stream, int lexState)
{
   ReInit(stream);
   SwitchTo(lexState);
}

/** Switch to specified lex state. */
public void SwitchTo(int lexState)
{
   if (lexState >= 1 || lexState < 0)
      throw new TokenMgrError("Error: Ignoring invalid lexical state : " + lexState + ". State unchanged.", TokenMgrError.INVALID_LEXICAL_STATE);
   else
      curLexState = lexState;
}

protected Token jjFillToken()
{
   final Token t;
   final String curTokenImage;
   final int beginLine;
   final int endLine;
   final int beginColumn;
   final int endColumn;
   String im = jjstrLiteralImages[jjmatchedKind];
   curTokenImage = (im == null) ? input_stream.GetImage() : im;
   beginLine = input_stream.getBeginLine();
   beginColumn = input_stream.getBeginColumn();
   endLine = input_stream.getEndLine();
   endColumn = input_stream.getEndColumn();
   t = Token.newToken(jjmatchedKind, curTokenImage);

   t.beginLine = beginLine;
   t.endLine = endLine;
   t.beginColumn = beginColumn;
   t.endColumn = endColumn;

   return t;
}

int curLexState = 0;
int defaultLexState = 0;
int jjnewStateCnt;
int jjround;
int jjmatchedPos;
int jjmatchedKind;

/** Get the next Token. */
public Token getNextToken() 
{
  Token matchedToken;
  int curPos = 0;

  EOFLoop :
  for (;;)
  {
   try
   {
      curChar = input_stream.BeginToken();
   }
   catch(java.io.IOException e)
   {
      jjmatchedKind = 0;
      matchedToken = jjFillToken();
      return matchedToken;
   }

   try { input_stream.backup(0);
      while (curChar <= 32 && (0x100002600L & (1L << curChar)) != 0L)
         curChar = input_stream.BeginToken();
   }
   catch (java.io.IOException e1) { continue EOFLoop; }
   jjmatchedKind = 0x7fffffff;
   jjmatchedPos = 0;
   curPos = jjMoveStringLiteralDfa0_0();
   if (jjmatchedKind != 0x7fffffff)
   {
      if (jjmatchedPos + 1 < curPos)
         input_stream.backup(curPos - jjmatchedPos - 1);
      if ((jjtoToken[jjmatchedKind >> 6] & (1L << (jjmatchedKind & 077))) != 0L)
      {
         matchedToken = jjFillToken();
         return matchedToken;
      }
      else
      {
         continue EOFLoop;
      }
   }
   int error_line = input_stream.getEndLine();
   int error_column = input_stream.getEndColumn();
   String error_after = null;
   boolean EOFSeen = false;
   try { input_stream.readChar(); input_stream.backup(1); }
   catch (java.io.IOException e1) {
      EOFSeen = true;
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
      if (curChar == '\n' || curChar == '\r') {
         error_line++;
         error_column = 0;
      }
      else
         error_column++;
   }
   if (!EOFSeen) {
      input_stream.backup(1);
      error_after = curPos <= 1 ? "" : input_stream.GetImage();
   }
   throw new TokenMgrError(EOFSeen, curLexState, error_line, error_column, error_after, curChar, TokenMgrError.LEXICAL_ERROR);
  }
}

private void jjCheckNAdd(int state)
{
   if (jjrounds[state] != jjround)
   {
      jjstateSet[jjnewStateCnt++] = state;
      jjrounds[state] = jjround;
   }
}
private void jjAddStates(int start, int end)
{
   do {
      jjstateSet[jjnewStateCnt++] = jjnextStates[start];
   } while (start++ != end);
}
private void jjCheckNAddTwoStates(int state1, int state2)
{
   jjCheckNAdd(state1);
   jjCheckNAdd(state2);
}

}
