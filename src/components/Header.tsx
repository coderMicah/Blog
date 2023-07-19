import Image from "next/image";
import Link from "next/link";

function Header() {
  return (
    <div className="flex items-center justify-between space-x-2 font-bold px-5 md:px-10 py-5">
      <div className="flex items-center space-x-2">
        <Link href="/">
          <Image
            src="/next.svg"
            width={100}
            height={50}
            className="rounded-full"
            alt="logo"
          />
        </Link>
        <h1>Micah&apos;s Blog</h1>
      </div>
      <div>
        <Link
          href="/"
          className="px-3 md:px-5 py-3 text-sm md:text-base bg-gray-900 text-[#F7AB0A] flex items-center rounded-lg text-center"
        >
          Sign Up
        </Link>
      </div>
    </div>
  );
}

export default Header;
