import { PokerCard, PokerHand, CardColor } from "../ui/components/PokerHand/types";

const BASE_URL = "http://server:8080";

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
    <div key={hand.id} className="flex items-center">
      <div className="p-2 align-middle">
        Hand {hand.id}
      </div>
      <div className="flex gap-2 my-2">
        {hand.cards.map((card: PokerCard) => (
          <div
            key={card.id}
            className={`bg-white rounded-md ${TextColor(card.color)} w-12 h-18 p-2`}
          >
            {card.value}{ColorToSymbol(card.color)}
          </div>
        ))}
      </div>
    </div>
  );
}

export default async function Poker() {
  const data = await fetch(`${BASE_URL}/hands`);
  const hands: PokerHand[] = await data.json();

  return (
    <div>
      <h1 className="text-2xl my-4">Poker</h1>
      <div className="flex gap-4">
        <div className="flex-none">
          <div>
            Poker hands:
          </div>
          <ul>
            {hands.map((hand: PokerHand) => (
              HandComp(hand)
            ))}
          </ul>
        </div>
        <div>
          <button className="rounded-full bg-green-600 p-4">
            Create new hand
          </button>
        </div>
        <div>
          Compare cards here
        </div>
      </div>
    </div>
  );
}