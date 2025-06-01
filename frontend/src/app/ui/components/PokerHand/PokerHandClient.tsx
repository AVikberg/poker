'use client';
import { use } from "react";
import { PokerHand, CardColor, PokerCard } from "./types";


const HandComp = (hand: PokerHand) => {
  const TextColor = (color: CardColor) => {
    if (color == CardColor.Hearts || color == CardColor.Diamonds) {
      return "text-red-600";
    }
    return "text-black";
  }
  const ColorToSymbol = (color: CardColor) => {
    const mapping = {
      [CardColor.Hearts]: "♥",
      [CardColor.Diamonds]: "♦",
      [CardColor.Clubs]: "♣",
      [CardColor.Spades]: "♠",
    };
    return mapping[color];
  }

  return (
    <a key={hand.id} className="flex items-center bg-slate-700 rounded-xl p-2"
      href={`poker/hand/${hand.id}`}>
      <div className="align-middle pr-2">
        Hand {hand.id}
      </div>
      <div className="flex gap-2">
        {hand.cards.map((card: PokerCard) => (
          <div
            key={card.id}
            className={`bg-white rounded-md ${TextColor(card.color)} w-12 h-18 p-2`}
          >
            {card.value == "t" ? 10 : card.value.toUpperCase()}{ColorToSymbol(card.color)}
          </div>
        ))}
      </div>
    </a>
  );
}

export default function PokerHandClient(
  { hands,}: { hands: Promise<PokerHand[]> }) {
  const pokerHands: PokerHand[] = use(hands);

  return (
    <div className="flex flex-col flex-wrap gap-2" style={{maxHeight: "40%"}}>
      {pokerHands.map((hand: PokerHand) => (
        HandComp(hand)
      ))}
    </div>
  );
}
