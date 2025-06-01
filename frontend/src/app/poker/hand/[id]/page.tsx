import { PokerCard, CardColor, PokerAnalysis, HandCategory } from "@/app/ui/components/PokerHand/types";


const BASE_URL = "http://server:8080";

const HandComp = (hand: PokerAnalysis) => {
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
  const HighestToSymbol = (value: number) => {
    switch (value) {
      case 1: { return "A"; }
      case 11: { return "J"; }
      case 12: { return "Q"; }
      case 13: { return "K"; }
      default: { return value; }
    }
  }

  const DescribeCategory = (category: HandCategory) => {
    const mapping = {
      [HandCategory.HighCard]: "High card",
      [HandCategory.OnePair]: "One pair",
      [HandCategory.TwoPair]: "Two pairs",
      [HandCategory.ThreeOfAKind]: "Three of a kind",
      [HandCategory.Flush]: "Flush",
      [HandCategory.FullHouse]: "Full house",
      [HandCategory.FourOfAKind]: "Four of a kind",
      [HandCategory.StraightFlush]: "Straight flush",
    };
    return mapping[category].toLowerCase();
  }

  return (
    <>
      <div className="flex gap-2 my-2 items-center">
        {hand.cards.map((card: PokerCard) => (
          <div
            key={card.id}
            className={`bg-white rounded-md ${TextColor(card.color)} w-12 h-18 p-2`}
          >
            {card.value.toUpperCase()}{ColorToSymbol(card.color)}
          </div>
        ))}
      </div>
      <div>
        The hand has {DescribeCategory(hand.category)} (highest card {HighestToSymbol(hand.highestInCategory)})
      </div>
    </>
  );
}

export default async function HandAnalysis({
  params,
}: {params: Promise<{id: number}>}) {
  'use server';
  const {id} = await params;
  const data = await fetch(`${BASE_URL}/hands/analysis/${id}`);
  const hand: PokerAnalysis = await data.json();

  return (
    <div className="flex flex-col items-center justify-center">
      <h1 className="text-2xl my-4 items-center">Poker hand {hand.handId}</h1>
      <div className="items-center">
        Cards in hand:
      </div>
      {HandComp(hand)}
    </div>
  );
}
